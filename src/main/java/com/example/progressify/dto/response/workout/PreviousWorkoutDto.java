package com.example.progressify.dto.response.workout;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreviousWorkoutDto {
    Long id;
    @JsonProperty("workout_exercises")
    List<AddWorkoutExerciseDTO> workoutExercises;
}
