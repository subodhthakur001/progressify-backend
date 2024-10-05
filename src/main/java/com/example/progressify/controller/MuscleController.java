package com.example.progressify.controller;

import com.example.progressify.constants.ServiceConstant;
import com.example.progressify.dto.request.muscle.AddMuscleRequestDTO;
import com.example.progressify.dto.request.muscle.UpdateMuscleRequestDTO;
import com.example.progressify.dto.response.muscle.MuscleListResponseDTO;
import com.example.progressify.dto.response.muscle.MuscleResponseDTO;
import com.example.progressify.model.Muscle;
import com.example.progressify.service.MuscleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muscle")
public class MuscleController {

    private final MuscleService muscleService;

    @Autowired
    public MuscleController(MuscleService muscleService){
        this.muscleService = muscleService;
    }

    @PostMapping
    public ResponseEntity<MuscleResponseDTO> addMuscle(@Valid @RequestBody AddMuscleRequestDTO addMuscleRequestDTO){
        muscleService.addMuscle(addMuscleRequestDTO);
        MuscleResponseDTO response = new MuscleResponseDTO(ServiceConstant.SAVE_MESSAGE);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MuscleResponseDTO> updateMuscle(@Valid @RequestBody UpdateMuscleRequestDTO updateMuscleRequestDTO){
        muscleService.updateMuscle(updateMuscleRequestDTO);
        MuscleResponseDTO response = new MuscleResponseDTO("Muscle Updated Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MuscleListResponseDTO> muscleList(){
        List<Muscle> muscleList = muscleService.muscleList();
        MuscleListResponseDTO response = new MuscleListResponseDTO("Muscle Listing Fetched Successfully",muscleList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
