package com.gymtrack.api.feature.user.routine.mapper;

import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineResponseDTO;
import com.gymtrack.api.feature.user_routine.model.UserRoutine;
import com.gymtrack.api.feature.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoutineMapper {
    private final WorkoutService workoutService;

    @Autowired
    public UserRoutineMapper(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    public UserRoutineResponseDTO userRoutineToUserRoutineDTO(UserRoutine userRoutine) {
        if (userRoutine == null || userRoutine.getRoutine() == null) {
            return null;
        }

        UserRoutineResponseDTO userRoutineResponseDTO = new UserRoutineResponseDTO();
        userRoutineResponseDTO.setIsSelected((userRoutine.getIsSelected()));

        Routine routine = userRoutine.getRoutine();
        userRoutineResponseDTO.setId(routine.getId());
        userRoutineResponseDTO.setDescription((routine.getDescription()));
        userRoutineResponseDTO.setCreatedAt((routine.getCreatedAt()));
        userRoutineResponseDTO.setName((routine.getName()));
        userRoutineResponseDTO.setImageId((routine.getImageId()));
        userRoutineResponseDTO.setRating((routine.getRating()));
        userRoutineResponseDTO.setPublicId((routine.getPublicId()));
        userRoutineResponseDTO.setNumTimesCopied((routine.getNumTimesCopied()));
        userRoutineResponseDTO.setWorkout(workoutService.createWorkout(routine.getRoutineExercises()));

        return userRoutineResponseDTO;
    }
}
