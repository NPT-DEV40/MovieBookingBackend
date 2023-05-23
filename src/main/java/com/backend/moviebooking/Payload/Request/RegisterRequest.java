package com.backend.moviebooking.Payload.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    @Size(min = 6, max = 50)
    private String email;

    private Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
