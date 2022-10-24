package com.gymtrack.api.feature.user_routine_exercise.service;

import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.user_routine_exercise.dto.UserRoutineExerciseRequestDTO;

public interface UserRoutineExerciseService {
    RoutineExercise createUserRoutineExercise(Integer userId, Integer routineId, Integer exerciseId, UserRoutineExerciseRequestDTO userRoutineExerciseRequestDTO);
}
