package com.backend.moviebooking.Payload.Response;

import com.backend.moviebooking.Model.RefreshToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwt, RefreshToken refreshToken, String id, String username, String email, List<String> roles) {
        this.token = jwt;
        this.refreshToken = refreshToken.getToken();
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
