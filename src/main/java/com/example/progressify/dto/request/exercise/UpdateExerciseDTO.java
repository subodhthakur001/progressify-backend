package com.example.progressify.dto.request.exercise;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExerciseDTO {

    @NotBlank(message = "exercise cannot be null")
    String name;
}
