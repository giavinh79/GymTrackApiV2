package com.gymtrack.api.feature.user_routine_exercise_set.service;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.exercise.repository.ExerciseRepository;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.routine_exercise.repository.RoutineExerciseRepository;
import com.gymtrack.api.feature.set.dto.SetRequestDTO;
import com.gymtrack.api.feature.set.mapper.SetMapper;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.set.repository.SetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserRoutineExerciseSetServiceImpl {
    private final RoutineRepository routineRepository;
    private final RoutineExerciseRepository routineExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final SetRepository setRepository;

    private RoutineExercise getRoutineExercise(Integer routineId, Integer exerciseId, Day day) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);

        return routineExerciseRepository
                .findByRoutineEqualsAndExerciseEqualsAndDayEquals(routine, exercise, day)
                .orElseThrow(() -> new NotFoundException("RoutineExercise could not be found"));
    }

    @Transactional
    public Set createUserRoutineExerciseSet(Integer routineId, Integer exerciseId, Day day, SetRequestDTO setRequestDTO) {
        RoutineExercise routineExercise = getRoutineExercise(routineId, exerciseId, day);

        Set newSet = setRepository.save(SetMapper.INSTANCE.setRequestDTOToSet(setRequestDTO));

        List<Set> existingSets = routineExercise.getSets();
        existingSets.add(newSet);
        routineExercise.setSets(existingSets);

        routineExerciseRepository.save(routineExercise);
        return newSet;
    }

    public Set updateUserRoutineExerciseSet(Integer routineId, Integer exerciseId, Day day, Set setToUpdate) {
        RoutineExercise routineExercise = getRoutineExercise(routineId, exerciseId, day);
        boolean setExistsInRoutine = routineExercise.getSets().stream().anyMatch(set -> set.getId().equals(setToUpdate.getId()));

        if (!setExistsInRoutine) {
            throw new NotFoundException();
        }

        return setRepository.save(setToUpdate);
    }
}
