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
//    @NotBlank
    private String firstName;

//    @NotBlank
    private String lastName;

    private String email;

//    @NotBlank
//    @Size(min = 3, max = 20)
    private String userName;

//    @NotBlank
//    @Size(min = 6, max = 20)
    private String password;

    private String confirmPassword;

    private Set<String> roles;
}
