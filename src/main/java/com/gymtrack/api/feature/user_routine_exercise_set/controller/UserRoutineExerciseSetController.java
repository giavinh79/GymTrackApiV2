package com.gymtrack.api.feature.user_routine_exercise_set.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.feature.set.dto.SetRequestDTO;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.user_routine_exercise_set.service.UserRoutineExerciseSetServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/user")
public class UserRoutineExerciseSetController {
    private final UserRoutineExerciseSetServiceImpl userRoutineExerciseSetServiceImpl;
    private final AccessControlService accessControlService;

    @PutMapping("/{userId}/routine/{routineId}/exercise/{exerciseId}/set")
    public Set createUserRoutineExerciseSet(@PathVariable Integer userId, @PathVariable Integer routineId, @PathVariable Integer exerciseId, @Valid @RequestBody SetRequestDTO setRequestDTO, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, userId, routineId);
        return userRoutineExerciseSetServiceImpl.createUserRoutineExerciseSet(routineId, exerciseId, setRequestDTO);
    }

    @PatchMapping("/{userId}/routine/{routineId}/exercise/{exerciseId}/set/{setId}")
    public Set updateUserRoutineExerciseSet(@PathVariable Integer userId, @PathVariable Integer routineId, @PathVariable Integer exerciseId, @PathVariable Integer setId, @Valid @RequestBody Set set, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, userId, routineId);
        return userRoutineExerciseSetServiceImpl.updateUserRoutineExerciseSet(routineId, exerciseId, setId, set);
    }
}
