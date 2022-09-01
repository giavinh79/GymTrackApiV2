package com.gymtrack.api.feature.user.routine.mapper;

import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineDTO;
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

    public UserRoutineDTO routineToUserRoutineDTO(UserRoutine userRoutine) {
        if (userRoutine == null || userRoutine.getRoutine() == null) {
            return null;
        }

        UserRoutineDTO userRoutineDTO = new UserRoutineDTO();
        userRoutineDTO.setIsSelected((userRoutine.getIsSelected()));

        Routine routine = userRoutine.getRoutine();
        userRoutineDTO.setId(routine.getId());
        userRoutineDTO.setDescription((routine.getDescription()));
        userRoutineDTO.setCreatedAt((routine.getCreatedAt()));
        userRoutineDTO.setName((routine.getName()));
        userRoutineDTO.setImageId((routine.getImageId()));
        userRoutineDTO.setRating((routine.getRating()));
        userRoutineDTO.setPublicId((routine.getPublicId()));
        userRoutineDTO.setNumTimesCopied((routine.getNumTimesCopied()));

        userRoutineDTO.setWorkout(workoutService.createWorkout(routine.getRoutineExercises()));

        return userRoutineDTO;
    }
}
