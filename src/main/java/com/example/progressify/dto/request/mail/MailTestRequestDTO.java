package com.example.progressify.dto.request.mail;

import com.example.progressify.constants.ValidationConstant;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailTestRequestDTO {
    @Email(message = ValidationConstant.VALID_EMAIL)
    String email;
    String subject;
    String body;
}
