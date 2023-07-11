package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Exception.TokenRefreshException;
import com.backend.moviebooking.Model.Enum.ERole;
import com.backend.moviebooking.Model.RefreshToken;
import com.backend.moviebooking.Model.Role;
import com.backend.moviebooking.Model.User;
import com.backend.moviebooking.Payload.Request.LoginRequest;
import com.backend.moviebooking.Payload.Request.RegisterRequest;
import com.backend.moviebooking.Payload.Request.TokenRefreshRequest;
import com.backend.moviebooking.Payload.Response.JwtResponse;
import com.backend.moviebooking.Payload.Response.TokenRefreshResponse;
import com.backend.moviebooking.Payload.Response.UserDetailsResponse;
import com.backend.moviebooking.Repository.RoleRepository;
import com.backend.moviebooking.Repository.UserRepository;
import com.backend.moviebooking.Security.Services.RefreshTokenService;
import com.backend.moviebooking.Security.jwt.JwtUtils;
import com.backend.moviebooking.Security.Services.UserDetailsImpl;
import com.backend.moviebooking.Security.Services.UserDetailsServiceImpl;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "http://localhost:4200/register", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {
    final AuthenticationManager authenticationManager;

    final UserRepository userRepository;

    final UserDetailsServiceImpl userDetailsService;

    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;

    final JwtUtils jwtUtils;

    final MongoTemplate mongoTemplate;

    final RefreshTokenService refreshTokenService;

    @Value("${google.id}")
    private String idClient;

    @Value("${secret.password}")
    private String passWord;

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:4200/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(authentication);

        RefreshToken refreshToken = refreshTokenService.CreateRefreshToken(userDetails.getId());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtResponse jwtResponse = new JwtResponse(jwt, refreshToken,
                userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles);
        return ResponseEntity.ok().body(jwtResponse);
    }

    @PostMapping(value = "/login/google")
    @CrossOrigin(origins = "http://localhost:4200/login")
    public ResponseEntity<?> loginGoogle(@RequestBody String token) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory factory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singleton(idClient));
        GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), token);
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        String email = payload.getEmail();
        String userName = payload.get("name").toString();
        User user = new User();
        if(userRepository.existsByEmail(email)) {
            user = userDetailsService.getUserByEmail(email);
        }
        else {
            user = createUser(email, userName);
        }
        List<String> roles = new ArrayList<String>(){{
            add("ROLE_USER");
        }};
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        RefreshToken refreshToken = refreshTokenService.CreateRefreshToken(user.getId());
        JwtResponse jwtResponse = new JwtResponse(jwtUtils.generateToken(authentication), refreshToken,
                user.getId(), user.getUsername(),
                user.getEmail(), roles);
        return ResponseEntity.ok().body(jwtResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::VerifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUser(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, refreshToken));
                }).orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }

    private User createUser(String email, String userName) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(passWord));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.getUserName())) {
            System.out.println(registerRequest.getUserName());
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User user = new User(registerRequest.getUserName(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRoles();

        Set<Role> roles = new HashSet<>();

        if(strRoles != null && !strRoles.isEmpty()) {
            strRoles.forEach(role -> {
                switch(role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        } else {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok().body(registerRequest);
    }

    @GetMapping("/logout/success")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().body("Logout successfully!");
    }

    // Mod
    @GetMapping("/users")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> getAllUsers() {
//        Query query = new Query();
//        query.with(Sort.by(Sort.Direction.DESC, "roles"));
//        return ResponseEntity.ok().body(mongoTemplate.find(query, User.class));
        return ResponseEntity.ok().body(userDetailsService.findAllDescByRoles());
    }
}
