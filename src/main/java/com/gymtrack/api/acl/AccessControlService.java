package com.gymtrack.api.acl;

import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.exception.ForbiddenException;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccessControlService {
    private final RoutineRepository routineRepository;

    private boolean canAccessUser(UserContext userContext, Integer userId) {
        return userContext.getId().equals((userId));
    }

    private boolean canAccessRoutine(UserContext userContext, Integer userId, Routine routine) {
        if (!canAccessUser(userContext, userId)) {
            return false;
        }

        return routine.getCreatorId().equals(userContext.getId());
    }

    public void validateAccessToUser(UserContext userContext, Integer userId) {
        if (!canAccessUser(userContext, userId)) {
            throw new ForbiddenException();
        }
    }

    public Routine validateAccessToRoutine(UserContext userContext, Integer userId, Integer routineId) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(() -> new NotFoundException("Routine was not found"));

        if (!canAccessRoutine(userContext, userId, routine)) {
            throw new ForbiddenException();
        }

        return routine;
    }
}
