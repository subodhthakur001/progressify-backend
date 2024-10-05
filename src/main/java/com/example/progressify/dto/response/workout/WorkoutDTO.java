package com.example.progressify.dto.response.workout;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
    @JsonProperty("exercise_id")
    List<AddWorkoutExerciseDTO> exerciseId;
    @JsonProperty("user_id")
    Long userId;
    List<SetDTO> sets;
}
