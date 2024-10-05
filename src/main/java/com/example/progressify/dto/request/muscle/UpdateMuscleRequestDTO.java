package com.example.progressify.dto.request.muscle;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMuscleRequestDTO {
    @NotNull(message = "muscle id cannot be null")
    @JsonProperty("muscle_id")
    private Long muscleId;
    @NotBlank(message = "muscle name cannot be blank")
    private String name;
}
