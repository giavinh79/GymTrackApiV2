package com.gymtrack.api.feature.workout;

import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class WorkoutService {
    private Map<String, List<Exercise>> createDayToExercisesWorkoutMap(List<RoutineExercise> routineExercises) {
        List<RoutineExercise> sortedRoutineExercises = routineExercises.stream()
                .sorted(Comparator.comparing(RoutineExercise::getExerciseOrder))
                .toList();

        Map<String, List<Exercise>> map = new HashMap<>(Map.ofEntries(
                new AbstractMap.SimpleEntry<>(Day.MONDAY.name(), Collections.emptyList()),
                new AbstractMap.SimpleEntry<>(Day.TUESDAY.name(), Collections.emptyList()),
                new AbstractMap.SimpleEntry<>(Day.WEDNESDAY.name(), Collections.emptyList()),
                new AbstractMap.SimpleEntry<>(Day.THURSDAY.name(), Collections.emptyList()),
                new AbstractMap.SimpleEntry<>(Day.FRIDAY.name(), Collections.emptyList()),
                new AbstractMap.SimpleEntry<>(Day.SATURDAY.name(), Collections.emptyList()),
                new AbstractMap.SimpleEntry<>(Day.SUNDAY.name(), Collections.emptyList())
        ));

        for (RoutineExercise routineExercise : sortedRoutineExercises) {
            Exercise exercise = routineExercise.getExercise();
            Day day = routineExercise.getDay();

            List<Exercise> exercises = map.get((day.name()));

            if (exercises == null) {
                log.error("Invalid day key");
                return map;
            }

            exercises.add(exercise);
            map.put(day.name(), exercises);
        }

        return map;
    }

    public Workout createWorkout(List<RoutineExercise> routineExercises) {
        Map<String, List<Exercise>> dayToExercisesMap = createDayToExercisesWorkoutMap((routineExercises));

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
