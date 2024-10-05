package com.example.progressify.dto.response.workout;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddWorkoutExerciseDTO {
       Long id;
       @JsonProperty("user_id")
       Long userId;
       @JsonProperty("workout_id")
       Long workoutId;
       @JsonProperty("muscle_id")
       Long muscleId;
       List<SetDTO> sets;
}
