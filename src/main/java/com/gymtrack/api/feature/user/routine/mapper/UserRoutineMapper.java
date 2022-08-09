package com.gymtrack.api.feature.user.routine.mapper;

import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineDTO;
import com.gymtrack.api.feature.user_routine.model.UserRoutine;
import org.springframework.stereotype.Component;

@Component
public class UserRoutineMapper {
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

        return userRoutineDTO;
    }
}
