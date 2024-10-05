package com.example.progressify.dao;

import com.example.progressify.model.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
    Muscle findByname(String name);
}
