package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Dtos.TokenDto;
import com.backend.moviebooking.Model.Enum.ERole;
import com.backend.moviebooking.Model.Role;
import com.backend.moviebooking.Model.User;
import com.backend.moviebooking.Payload.Request.LoginRequest;
import com.backend.moviebooking.Payload.Request.RegisterRequest;
import com.backend.moviebooking.Payload.Response.UserDetailsResponse;
import com.backend.moviebooking.Repository.RoleRepository;
import com.backend.moviebooking.Repository.UserRepository;
import com.backend.moviebooking.Security.jwt.JwtUtils;
import com.backend.moviebooking.Service.Impl.UserDetailsImpl;
import com.backend.moviebooking.Service.Impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:4200/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                jwt,
                roles);
        return ResponseEntity.ok().body(userDetailsResponse);
    }

    @PostMapping(value = "/login/google")
    public ResponseEntity<?> loginGoogle(@RequestBody String tokenDto) {

        return null;
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
