package com.example.progressify.dto.request.muscle;

import com.example.progressify.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMuscleRequestDTO {

    @NotBlank(message = "name cannot be empty")
    @Size(min = 3, message = "name should be atleast 3 Character long")
    private String name;
    @NotNull(message = "user_id cannot be null")
    private Long user_id;

}
