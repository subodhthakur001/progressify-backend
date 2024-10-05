package com.example.progressify.dto.request.auth;

import com.example.progressify.constants.ValidationConstant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Username cannot be empty")
    @Email(message = ValidationConstant.VALID_EMAIL)
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = ValidationConstant.VALID_MIN_PASSWORD)
    private String password;
}
