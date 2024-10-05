package com.example.progressify.dao;

import com.example.progressify.model.ExerciseWorkout;
import com.example.progressify.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseWorkoutRepository extends JpaRepository<ExerciseWorkout, Long> {
    List<ExerciseWorkout> findByWorkout(Workout workout);
}
