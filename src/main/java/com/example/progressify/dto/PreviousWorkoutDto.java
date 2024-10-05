package com.example.progressify.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreviousWorkoutDto {
    Long id;
    List<WorkoutExerciseDTO> workout_exercises;
}
