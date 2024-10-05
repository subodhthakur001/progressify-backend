package com.example.progressify.service;

import com.example.progressify.dto.request.exercise.AddExerciseDTO;
import com.example.progressify.model.Exercise;
import com.example.progressify.model.Muscle;
import com.example.progressify.exception.DuplicateEntityException;
import com.example.progressify.exception.ResourceNotFoundException;
import com.example.progressify.dao.ExerciseRepository;
import com.example.progressify.dao.MuscleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final MuscleRepository muscleRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository,
                        MuscleRepository muscleRepository){
        this.exerciseRepository = exerciseRepository;
        this.muscleRepository = muscleRepository;
    }

    public List<Exercise> getExerciseByMuscleId(Long id){
        return exerciseRepository.findByMuscleId(id);
    }

    public Exercise addExercise(AddExerciseDTO addExerciseDTO){

        if(exerciseRepository.findByName(addExerciseDTO.getName()) != null){
            throw new DuplicateEntityException("exercise already exists with the name" + addExerciseDTO.getName());
        }

        Muscle muscle = muscleRepository.findById(addExerciseDTO.getMuscleId())
                .orElseThrow(() -> new ResourceNotFoundException("Muscle not found with id: " + addExerciseDTO.getMuscleId()));


        Exercise exercise = new Exercise();
        exercise.setName(addExerciseDTO.getName());
        exercise.setMuscle(muscle);

        return exerciseRepository.save(exercise);
    }





}
