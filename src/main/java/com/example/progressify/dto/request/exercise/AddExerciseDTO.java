package com.example.progressify.dto.request.exercise;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddExerciseDTO {
    private Long muscleId;
    private String name;
}

