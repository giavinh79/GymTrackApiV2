package com.gymtrack.api.feature.user_routine_exercise.service;

import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.exercise.repository.ExerciseRepository;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.routine_exercise.repository.RoutineExerciseRepository;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.set.service.SetServiceImpl;
import com.gymtrack.api.feature.user_routine_exercise.dto.UserRoutineExerciseRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class UserRoutineExerciseServiceImpl implements UserRoutineExerciseService {
    private final RoutineRepository routineRepository;
    private final RoutineExerciseRepository routineExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final SetServiceImpl setServiceImpl;

    @Transactional
    public RoutineExercise createUserRoutineExercise(Integer userId, Integer routineId, Integer exerciseId, UserRoutineExerciseRequestDTO userRoutineExerciseRequestDTO) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);

        List<Set> sets = setServiceImpl.createSets(userRoutineExerciseRequestDTO.sets(), exercise.getExerciseValueType());

        Integer exerciseOrder = Objects.requireNonNullElse(userRoutineExerciseRequestDTO.exerciseOrder(), routineExerciseRepository.findAllByRoutineId(routineId).size() + 1);

        // save to routine_exercise table, id, routine_id, exercise_id, day, exercise_order
        RoutineExercise routineExercise = RoutineExercise
                .builder()
                .routine(routine)
                .exercise(exercise)
                .day(userRoutineExerciseRequestDTO.day())
                .exerciseOrder(exerciseOrder)
                .sets(sets)
                .build();

        return routineExerciseRepository.save(routineExercise);
    }
}
