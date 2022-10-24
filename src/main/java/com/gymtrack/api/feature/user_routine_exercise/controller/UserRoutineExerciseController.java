package com.gymtrack.api.feature.user_routine_exercise.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.feature.routine_exercise.model.RoutineExercise;
import com.gymtrack.api.feature.user_routine_exercise.dto.UserRoutineExerciseRequestDTO;
import com.gymtrack.api.feature.user_routine_exercise.service.UserRoutineExerciseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/user")
public class UserRoutineExerciseController {
    private final UserRoutineExerciseServiceImpl userRoutineExerciseServiceImpl;
    private final AccessControlService accessControlService;

    @PutMapping("/{userId}/routine/{routineId}/exercise/{exerciseId}")
    public RoutineExercise createUserRoutineExercise(@PathVariable Integer userId, @PathVariable Integer routineId, @PathVariable Integer exerciseId, @Valid @RequestBody UserRoutineExerciseRequestDTO userRoutineExerciseRequestDTO, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, userId, routineId);
        return userRoutineExerciseServiceImpl.createUserRoutineExercise(userId, routineId, exerciseId, userRoutineExerciseRequestDTO);
    }
}
