package com.gymtrack.api.feature.user_routine_exercise.service;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.exercise.repository.ExerciseRepository;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.routine_exercise.repository.RoutineExerciseRepository;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.set.service.SetServiceImpl;
import com.gymtrack.api.feature.user_routine_exercise.dto.UserRoutineExerciseCreateRequestDTO;
import com.gymtrack.api.feature.user_routine_exercise.dto.UserRoutineExerciseDeleteRequestDTO;
import com.gymtrack.api.feature.user_routine_exercise.dto.UserRoutineExerciseUpdateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserRoutineExerciseServiceImpl implements UserRoutineExerciseService {
    private final RoutineRepository routineRepository;
    private final RoutineExerciseRepository routineExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final SetServiceImpl setServiceImpl;

    @Transactional
    public RoutineExercise createUserRoutineExercise(Integer userId, Integer routineId, Integer exerciseId, UserRoutineExerciseCreateRequestDTO userRoutineExerciseCreateRequestDTO) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);

        List<Set> sets = setServiceImpl.createSets(userRoutineExerciseCreateRequestDTO.sets(), exercise.getExerciseValueType());

        Integer exerciseOrder = Objects.requireNonNullElse(userRoutineExerciseCreateRequestDTO.exerciseOrder(), routineExerciseRepository.findAllByRoutineId(routineId).size() + 1);

        // save to routine_exercise table, id, routine_id, exercise_id, day, exercise_order
        RoutineExercise routineExercise = RoutineExercise
                .builder()
                .routine(routine)
                .exercise(exercise)
                .day(userRoutineExerciseCreateRequestDTO.day())
                .exerciseOrder(exerciseOrder)
                .sets(sets)
                .build();

        return routineExerciseRepository.save(routineExercise);
    }

    @Transactional
    public int deleteRoutineExercise(Integer routineId, Integer exerciseId, UserRoutineExerciseDeleteRequestDTO userRoutineExerciseDeleteRequestDTO) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);
        Day day = userRoutineExerciseDeleteRequestDTO.day();

        RoutineExercise routineExercise = routineExerciseRepository
                .findByRoutineEqualsAndExerciseEqualsAndDayEquals(routine, exercise, day)
                .orElseThrow(NotFoundException::new);

        routineExerciseRepository.delete(routineExercise);
        return routineExercise.getId();
    }

    @Transactional
    public RoutineExercise updateUserRoutineExercise(Integer routineId, Integer exerciseId, UserRoutineExerciseUpdateRequestDTO userRoutineExercisesUpdateRequestDTO) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);
        Day day = userRoutineExercisesUpdateRequestDTO.day();

        RoutineExercise routineExercise = routineExerciseRepository
                .findByRoutineEqualsAndExerciseEqualsAndDayEquals(routine, exercise, day)
                .orElseThrow(NotFoundException::new);

        if (userRoutineExercisesUpdateRequestDTO.day() != null) {
            routineExercise.setDay(userRoutineExercisesUpdateRequestDTO.day());
        }

        if (userRoutineExercisesUpdateRequestDTO.exerciseOrder() != null) {
            routineExercise.setExerciseOrder(userRoutineExercisesUpdateRequestDTO.exerciseOrder());
        }
        // @TODO - update sets
        return routineExerciseRepository.save(routineExercise);
    }

    @Transactional
    public List<RoutineExercise> updateUserRoutineExercises(Integer routineId, List<UserRoutineExerciseUpdateRequestDTO> userRoutineExercisesUpdateRequestDTO) {
        routineRepository.findById(routineId).orElseThrow(NotFoundException::new);
        Iterable<Integer> routineExercisesToUpdateIds = userRoutineExercisesUpdateRequestDTO
                .stream()
                .map(UserRoutineExerciseUpdateRequestDTO::id)
                .toList();
        Iterable<RoutineExercise> routineExercises = routineExerciseRepository.findAllById(routineExercisesToUpdateIds); // this is ignoring day atm which is bad!

        Map<Integer, UserRoutineExerciseUpdateRequestDTO> updatedExercisesMap = userRoutineExercisesUpdateRequestDTO.stream()
                .collect(Collectors.toMap(UserRoutineExerciseUpdateRequestDTO::id, Function.identity()));

        routineExercises.forEach((routineExercise -> {
            Day day = updatedExercisesMap.get(routineExercise.getId()).day();
            if (day != null) {
                routineExercise.setDay(day);
            }

            Integer exerciseOrder = updatedExercisesMap.get(routineExercise.getId()).exerciseOrder();
            if (exerciseOrder != null) {
                routineExercise.setExerciseOrder(exerciseOrder);
            }

            // @TODO - update sets
        }));

        return StreamSupport.stream(routineExerciseRepository.saveAll(routineExercises).spliterator(), false)
                .toList();
    }
}
