package com.gymtrack.api.feature.routine_exercise.repository;

import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineExerciseRepository extends CrudRepository<RoutineExercise, Integer> {
    List<RoutineExercise> findAllByRoutineId(Integer routineId);
}
