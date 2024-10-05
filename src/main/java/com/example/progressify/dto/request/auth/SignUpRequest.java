package com.example.progressify.dto.request.auth;

import com.example.progressify.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "username cannot be empty.")
    private String username;

    @NotBlank(message = "username cannot be empty.")
    @Email(message = "please provide a valid email.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password should be at least 8 characters long.")
    private String password;

    @NotNull(message = "role cannot be empty")
    private Role role;
}
