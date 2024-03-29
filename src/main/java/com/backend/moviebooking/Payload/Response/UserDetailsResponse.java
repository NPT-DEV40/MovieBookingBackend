package com.backend.moviebooking.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailsResponse {
    private String id;
    private String username;
    private String email;
    private String token;
    private List<String> roles;
}
