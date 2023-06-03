package com.gymtrack.api.feature.workout;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine_exercise.dto.RoutineExerciseResponseDTO;
import com.gymtrack.api.feature.routine_exercise.mapper.RoutineExerciseMapper;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.set.mapper.SetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class WorkoutService {
    private Map<String, List<RoutineExerciseResponseDTO>> createDayToExercisesWorkoutMap(List<RoutineExercise> routineExercises) {
        List<RoutineExercise> sortedRoutineExercises = routineExercises.stream()
                .sorted(Comparator.comparing(RoutineExercise::getExerciseOrder))
                .toList();

        Map<String, List<RoutineExerciseResponseDTO>> map = new HashMap<>();
        map.put(Day.MONDAY.name(), new ArrayList<>());
        map.put(Day.TUESDAY.name(), new ArrayList<>());
        map.put(Day.WEDNESDAY.name(), new ArrayList<>());
        map.put(Day.THURSDAY.name(), new ArrayList<>());
        map.put(Day.FRIDAY.name(), new ArrayList<>());
        map.put(Day.SATURDAY.name(), new ArrayList<>());
        map.put(Day.SUNDAY.name(), new ArrayList<>());

        for (RoutineExercise routineExercise : sortedRoutineExercises) {
            Exercise exercise = routineExercise.getExercise();
            Day day = routineExercise.getDay();

            List<RoutineExerciseResponseDTO> exercises = map.get((day.name()));

            if (exercises == null) {
                log.error("Invalid day key");
                return map;
            }

            RoutineExerciseResponseDTO routineExerciseResponseDTO = RoutineExerciseMapper.INSTANCE.exerciseToRoutineExerciseResponseDTO(exercise);
            routineExerciseResponseDTO.setId(routineExercise.getId());
            routineExerciseResponseDTO.setExerciseId(exercise.getId());
            routineExerciseResponseDTO.setSets(routineExercise.getSets().stream().map(SetMapper.INSTANCE::setToSetResponseDTO).toList());
            routineExerciseResponseDTO.setExerciseOrder(routineExercise.getExerciseOrder());

            exercises.add(routineExerciseResponseDTO);
            map.put(day.name(), exercises);
        }

        return map;
    }

    public Workout createWorkout(List<RoutineExercise> routineExercises) {
        Map<String, List<RoutineExerciseResponseDTO>> dayToExercisesMap = createDayToExercisesWorkoutMap((routineExercises));

        return Workout.builder()
                .monday(dayToExercisesMap.get(Day.MONDAY.name()))
                .tuesday(dayToExercisesMap.get(Day.TUESDAY.name()))
                .wednesday(dayToExercisesMap.get(Day.WEDNESDAY.name()))
                .thursday(dayToExercisesMap.get(Day.THURSDAY.name()))
                .friday(dayToExercisesMap.get(Day.FRIDAY.name()))
                .saturday(dayToExercisesMap.get(Day.SATURDAY.name()))
                .sunday(dayToExercisesMap.get(Day.SUNDAY.name()))
                .build();
    }


}
