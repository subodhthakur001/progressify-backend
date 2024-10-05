package com.example.progressify.dto.response.muscle;

import com.example.progressify.model.Muscle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MuscleListResponseDTO {
    private String message;
    private List<Muscle> muscle_list;
}
