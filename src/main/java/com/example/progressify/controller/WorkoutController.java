package com.example.progressify.controller;

import com.example.progressify.constants.ServiceConstant;
import com.example.progressify.dto.response.workout.PreviousWorkoutDto;
import com.example.progressify.dto.response.workout.AddWorkoutExerciseDTO;
import com.example.progressify.dto.response.workout.WorkoutResponseDTO;
import com.example.progressify.dto.response.commonresponse.ApiResponse;
import com.example.progressify.service.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/workout")
public class WorkoutController {

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public WorkoutController(WorkoutExerciseService workoutExerciseService){
        this.workoutExerciseService = workoutExerciseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addWorkout(@RequestBody AddWorkoutExerciseDTO addworkoutExerciseDTO){
        WorkoutResponseDTO workoutResponseDTO = workoutExerciseService.addWorkout(addworkoutExerciseDTO);
        ApiResponse response = new ApiResponse(ServiceConstant.SAVE_MESSAGE,workoutResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/previous-workout/{user_id}")
    public ResponseEntity<ApiResponse> getPreviousWorkoutInfo(@RequestParam("userId") Long userId, @RequestParam("muscleId") Long muscleId){
        PreviousWorkoutDto previousWorkoutDto = workoutExerciseService.getPreviousWorkoutInfo(userId,muscleId);
        ApiResponse response = new ApiResponse(ServiceConstant.LIST_MESSAGE,previousWorkoutDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
