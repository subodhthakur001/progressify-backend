package com.example.progressify.controller;

import com.example.progressify.constants.ServiceConstant;
import com.example.progressify.dto.request.exercise.AddExerciseDTO;
import com.example.progressify.dto.request.exercise.UpdateExerciseDTO;
import com.example.progressify.model.Exercise;
import com.example.progressify.dto.response.commonresponse.ApiResponse;
import com.example.progressify.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }

    @GetMapping("/muscle/{muscleId}")
    public ResponseEntity<ApiResponse> getExercisesByMuscleId(@PathVariable("muscleId") Long id){
        List<Exercise> exerciseListByMuscle = exerciseService.getExerciseByMuscleId(id);
        ApiResponse response = new ApiResponse(ServiceConstant.LIST_MESSAGE,exerciseListByMuscle);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addExercise(@RequestBody AddExerciseDTO addExerciseDTO){
        Exercise savedExercicse = exerciseService.addExercise(addExerciseDTO);
        ApiResponse response = new ApiResponse(ServiceConstant.SAVE_MESSAGE,savedExercicse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

   @PutMapping("{exerciseId}")
    public ResponseEntity<ApiResponse> udpateExercise(@PathVariable Long exerciseId, @RequestBody UpdateExerciseDTO updateExerciseDTO){
        Exercise updatedExercise = exerciseService.updateExercise(exerciseId,updateExerciseDTO);
        ApiResponse response = new ApiResponse(ServiceConstant.UPDATE_MESSAGE,updatedExercise);
        return new ResponseEntity<>(response,HttpStatus.OK);
   }

   @DeleteMapping("{exerciseId}")
    public ResponseEntity<ApiResponse> deleteExercise(@PathVariable Long exerciseId){
        Exercise deletedExercise = exerciseService.deleteExercise(exerciseId);
        ApiResponse response = new ApiResponse(ServiceConstant.DELETE_MESSAGE,deletedExercise);
        return new ResponseEntity<>(response,HttpStatus.OK);
   }


}

