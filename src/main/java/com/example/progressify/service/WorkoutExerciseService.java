package com.example.progressify.service;

import com.example.progressify.dto.response.workout.PreviousWorkoutDto;
import com.example.progressify.dto.response.workout.SetDTO;
import com.example.progressify.dto.response.workout.AddWorkoutExerciseDTO;
import com.example.progressify.dto.response.workout.WorkoutResponseDTO;
import com.example.progressify.model.*;
import com.example.progressify.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutExerciseService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseWorkoutRepository exerciseWorkoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final MuscleRepository muscleRepository;

    @Autowired
    public WorkoutExerciseService(WorkoutRepository workoutRepository,
                                  ExerciseWorkoutRepository exerciseWorkoutRepository,
                                  ExerciseRepository exerciseRepository,
                                  UserRepository userRepository,
                                  MuscleRepository muscleRepository){
        this.workoutRepository = workoutRepository;
        this.exerciseWorkoutRepository = exerciseWorkoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.muscleRepository = muscleRepository;

    }

    public WorkoutResponseDTO addWorkout(AddWorkoutExerciseDTO workoutExerciseDTO){
        Exercise exercise = exerciseRepository.findById(workoutExerciseDTO.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid exercise id: " + workoutExerciseDTO.getId()));

        User user = userRepository.findById(workoutExerciseDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + workoutExerciseDTO.getUserId()));

        Muscle muscle = muscleRepository.findById(workoutExerciseDTO.getMuscleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + workoutExerciseDTO.getMuscleId()));

        if(workoutExerciseDTO.getWorkoutId() == null){
            Workout workout = new Workout();
            workout.setUser(user);
            workout.setMuscle(muscle);
            workout = workoutRepository.save(workout);

            ExerciseWorkout exerciseWorkout = new ExerciseWorkout();
            exerciseWorkout.setWorkout(workout);
            exerciseWorkout.setExercise(exercise);

            List<SetDTO> setDTOs = workoutExerciseDTO.getSets();
            for (SetDTO setDTO : setDTOs) {
                WorkoutSet set = new WorkoutSet();
                set.setWeight(setDTO.getWeight());
                set.setReps(setDTO.getReps());
                set.setWorkout(exerciseWorkout);
                exerciseWorkout.addSet(set);
            }
            exerciseWorkout = exerciseWorkoutRepository.save(exerciseWorkout);

            return new WorkoutResponseDTO(
                    workout.getId(),
                    exerciseWorkout.getId(),
                    setDTOs
            );

        }else {
            Workout workout = workoutRepository.findById(workoutExerciseDTO.getWorkoutId()).orElseThrow(()
                    ->new IllegalArgumentException("Invalid workout id: " + workoutExerciseDTO.getWorkoutId()));
            ExerciseWorkout exerciseWorkout = new ExerciseWorkout();
            exerciseWorkout.setWorkout(workout);
            exerciseWorkout.setExercise(exercise);

            List<SetDTO> setDTOs = workoutExerciseDTO.getSets();
            for (SetDTO setDTO : setDTOs) {
                WorkoutSet set = new WorkoutSet();
                set.setWeight(setDTO.getWeight());
                set.setReps(setDTO.getReps());
                set.setWorkout(exerciseWorkout);
                exerciseWorkout.addSet(set);
            }
            exerciseWorkout = exerciseWorkoutRepository.save(exerciseWorkout);

            return new WorkoutResponseDTO(
                    null,
                    exerciseWorkout.getId(),
                    setDTOs
            );

        }

    }

    public PreviousWorkoutDto getPreviousWorkoutInfo(Long userId, Long muscleId) {

        Optional<Workout> optionalWorkout = workoutRepository.findTopByUserIdAndMuscleIdOrderByCreatedAtDesc(userId,muscleId);
        if (optionalWorkout.isEmpty()) {
            return null;
        }
        Workout workout = optionalWorkout.get();

        List<ExerciseWorkout> exerciseWorkouts = exerciseWorkoutRepository.findByWorkout(workout);

        List<AddWorkoutExerciseDTO> workoutExerciseDTOs = exerciseWorkouts.stream()
                .map(exerciseWorkout -> {
                    List<SetDTO> setDTOs = exerciseWorkout.getSets().stream()
                            .map(set -> new SetDTO(
                                    set.getWeight(),
                                    set.getReps() + 1
                            ))
                            .collect(Collectors.toList());

                    return new AddWorkoutExerciseDTO(
                            exerciseWorkout.getExercise().getId(),
                            workout.getUser().getId(),
                            workout.getMuscle().getId(),
                            workout.getId(),
                            setDTOs
                    );
                })
                .collect(Collectors.toList());

        return new PreviousWorkoutDto(
                workout.getId(),
                workoutExerciseDTOs
        );
    }
}

