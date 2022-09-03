package com.gymtrack.api.feature.user.routine.controller;

import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.context.UserContextMapper;
import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineRequestDTO;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineResponseDTO;
import com.gymtrack.api.feature.user.routine.service.UserRoutineServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/")
public class UserRoutineController {
    private final UserRoutineServiceImpl userRoutineService;

    @PutMapping("user/{userId}/routine")
    public UserRoutineResponseDTO createUserRoutine(@PathVariable Integer userId,
                                                    @RequestBody UserRoutineRequestDTO userRoutineRequestDTO,
                                                    @RequestAttribute(value = "userContext") UserContext userContext) {
        if (!userContext.getId().equals(userId)) {
            throw new AuthenticationException();
        }

        return userRoutineService.createUserRoutine(UserContextMapper.INSTANCE.userContextToUser(userContext), userRoutineRequestDTO);
    }

    @GetMapping("user/{userId}/routines")
    public List<UserRoutineResponseDTO> getUsersRoutines(@PathVariable Integer userId,
                                                         @RequestAttribute(value = "userContext") UserContext userContext) {
        if (!userContext.getId().equals(userId)) {
            throw new AuthenticationException();
        }

        return userRoutineService.getUsersRoutinesById(UserContextMapper.INSTANCE.userContextToUser(userContext));
    }

    @GetMapping("user/{userId}/routine/selected")
    public UserRoutineResponseDTO getSelectedUserRoutine(@PathVariable Integer userId,
                                                         @RequestAttribute(value = "userContext") UserContext userContext) {
        if (!userContext.getId().equals(userId)) {
            throw new AuthenticationException();
        }

        return userRoutineService.getSelectedUserRoutine(UserContextMapper.INSTANCE.userContextToUser(userContext));
    }
}
