package com.example.progressify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaiilTestRequestDTO {
    String email;
    String subject;
    String body;
}
