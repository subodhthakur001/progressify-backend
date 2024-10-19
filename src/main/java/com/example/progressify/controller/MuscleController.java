package com.example.progressify.controller;

import com.example.progressify.constants.ServiceConstant;
import com.example.progressify.dto.request.muscle.AddMuscleRequestDTO;
import com.example.progressify.dto.request.muscle.UpdateMuscleRequestDTO;
import com.example.progressify.dto.response.muscle.MuscleListResponseDTO;
import com.example.progressify.model.Muscle;
import com.example.progressify.dto.response.commonresponse.ApiResponse;
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
    public ResponseEntity<ApiResponse> addMuscle(@Valid @RequestBody AddMuscleRequestDTO addMuscleRequestDTO){
        Muscle savedMuscle = muscleService.addMuscle(addMuscleRequestDTO);
        ApiResponse response = new ApiResponse(ServiceConstant.SAVE_MESSAGE,savedMuscle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateMuscle(@Valid @RequestBody UpdateMuscleRequestDTO updateMuscleRequestDTO){
        Muscle updatedMuscle = muscleService.updateMuscle(updateMuscleRequestDTO);
        ApiResponse response = new ApiResponse(ServiceConstant.UPDATE_MESSAGE,updatedMuscle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MuscleListResponseDTO> muscleList(){
        List<Muscle> muscleList = muscleService.muscleList();
        MuscleListResponseDTO response = new MuscleListResponseDTO(ServiceConstant.LIST_MESSAGE,muscleList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteMuscle(@PathVariable Long id){
        Muscle deletedMuscle = muscleService.deleteMuscle(id);
        ApiResponse response = new ApiResponse(ServiceConstant.DELETE_MESSAGE,deletedMuscle);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
