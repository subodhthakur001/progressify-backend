package com.example.progressify.dto.response.workout;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponseDTO {
    @JsonProperty("workout_id")
    private Long workoutId;
    @JsonProperty("exercise_workout_id")
    private Long exerciseWorkoutId;
    private List<SetDTO> sets;
}
