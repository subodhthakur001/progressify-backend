package com.example.progressify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponseDTO {
    private Long workout_id;
    private Long exercise_workout_id;
    private List<SetDTO> sets;
}
