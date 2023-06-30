package com.gymtrack.api.feature.routine_exercise.repository;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutineExerciseRepository extends CrudRepository<RoutineExercise, Integer> {
    List<RoutineExercise> findAllByRoutineId(Integer routineId);

    Optional<RoutineExercise> findByRoutineEqualsAndExerciseEqualsAndDayEquals(Routine routine, Exercise exercise, Day day);
}
