package com.example.progressify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
    List<WorkoutExerciseDTO> exercise_id;
    Long user_id;
    List<SetDTO> sets;
}
