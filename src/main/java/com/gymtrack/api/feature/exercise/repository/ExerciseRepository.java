package com.gymtrack.api.feature.exercise.repository;

import com.gymtrack.api.feature.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findExercisesByCreatorIdIsNull();
}
