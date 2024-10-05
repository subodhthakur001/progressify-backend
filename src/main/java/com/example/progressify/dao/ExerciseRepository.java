package com.example.progressify.dao;

import com.example.progressify.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise , Long> {
    List<Exercise> findByMuscleId(Long id);
    Exercise findByName(String name);
}
