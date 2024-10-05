package com.example.progressify.dao;

import com.example.progressify.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Optional<Workout> findTopByUserIdAndMuscleIdOrderByCreatedAtDesc(Long userId, Long muscleId);
}
