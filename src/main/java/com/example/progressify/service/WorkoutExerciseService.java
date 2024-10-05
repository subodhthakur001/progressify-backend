package com.example.progressify.service;

import com.example.progressify.dto.*;
import com.example.progressify.model.*;
import com.example.progressify.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutExerciseService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseWorkoutRepository exerciseWorkoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MuscleRepository muscleRepository;

    public WorkoutResponseDTO addWorkout(WorkoutExerciseDTO workoutExerciseDTO){
        Exercise exercise = exerciseRepository.findById(workoutExerciseDTO.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid exercise id: " + workoutExerciseDTO.getId()));

        User user = userRepository.findById(workoutExerciseDTO.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + workoutExerciseDTO.getUser_id()));

        Muscle muscle = muscleRepository.findById(workoutExerciseDTO.getMuscle_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + workoutExerciseDTO.getMuscle_id()));

        if(workoutExerciseDTO.getWorkout_id() == null){
            Workout workout = new Workout();
            workout.setUser(user);
            workout.setMuscle(muscle);
            workout = workoutRepository.save(workout);

            Exercise_Workout exerciseWorkout = new Exercise_Workout();
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
            Workout workout = workoutRepository.findById(workoutExerciseDTO.getWorkout_id()).orElseThrow(()->new IllegalArgumentException("Invalid workout id: " + workoutExerciseDTO.getWorkout_id()));
            Exercise_Workout exerciseWorkout = new Exercise_Workout();
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
        if (!optionalWorkout.isPresent()) {
            return null;
        }
        Workout workout = optionalWorkout.get();

        List<Exercise_Workout> exerciseWorkouts = exerciseWorkoutRepository.findByWorkout(workout);

        List<WorkoutExerciseDTO> workoutExerciseDTOs = exerciseWorkouts.stream()
                .map(exerciseWorkout -> {
                    List<SetDTO> setDTOs = exerciseWorkout.getSets().stream()
                            .map(set -> new SetDTO(
                                    set.getWeight(),
                                    set.getReps() + 1
                            ))
                            .collect(Collectors.toList());

                    return new WorkoutExerciseDTO(
                            exerciseWorkout.getExercise().getId(),
                            workout.getUser().getId(),
                            workout.getMuscle().getId(),
                            workout.getId(),
                            setDTOs
                    );
                })
                .collect(Collectors.toList());

        PreviousWorkoutDto previousWorkoutDto = new PreviousWorkoutDto(
                workout.getId(),
                workoutExerciseDTOs
        );
        return previousWorkoutDto;
    }
}

