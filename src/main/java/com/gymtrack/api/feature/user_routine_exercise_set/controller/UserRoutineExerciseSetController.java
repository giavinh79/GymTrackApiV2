package com.gymtrack.api.feature.user_routine_exercise_set.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.enums.Day;
import com.gymtrack.api.feature.set.dto.SetRequestDTO;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.user_routine_exercise_set.service.UserRoutineExerciseSetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/user")
public class UserRoutineExerciseSetController {
    private final UserRoutineExerciseSetServiceImpl userRoutineExerciseSetServiceImpl;
    private final AccessControlService accessControlService;

    @PutMapping("/{userId}/routine/{routineId}/day/{day}/exercise/{exerciseId}/set")
    public Set createUserRoutineExerciseSet(@PathVariable Integer userId, @PathVariable Integer routineId, @PathVariable Integer exerciseId, @PathVariable Day day, @Valid @RequestBody SetRequestDTO setRequestDTO, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, userId, routineId);
        return userRoutineExerciseSetServiceImpl.createUserRoutineExerciseSet(routineId, exerciseId, day, setRequestDTO);
    }

    @PatchMapping("/{userId}/routine/{routineId}/exercise/{exerciseId}/set/{setId}")
    public Set updateUserRoutineExerciseSet(@PathVariable Integer userId, @PathVariable Integer routineId, @PathVariable Integer exerciseId, @PathVariable Day day, @PathVariable Integer setId, @Valid @RequestBody Set set, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, userId, routineId);
        set.setId(setId);
        return userRoutineExerciseSetServiceImpl.updateUserRoutineExerciseSet(routineId, exerciseId, day, set);
    }
}
