package com.example.progressify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutExerciseDTO {
       Long id;
       Long user_id;
       Long workout_id;
       Long muscle_id;
       List<SetDTO> sets;
}
