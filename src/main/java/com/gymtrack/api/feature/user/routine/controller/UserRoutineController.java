package com.gymtrack.api.feature.user.routine.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.context.UserContextMapper;
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
@RequestMapping("/api/v1/user")
public class UserRoutineController {
    private final UserRoutineServiceImpl userRoutineService;
    private final AccessControlService accessControlService;

    @PutMapping("/{userId}/routine")
    public UserRoutineResponseDTO createUserRoutine(@PathVariable Integer userId,
                                                    @RequestBody UserRoutineRequestDTO userRoutineRequestDTO,
                                                    @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToUser(userContext, userId);
        return userRoutineService.createUserRoutine(UserContextMapper.INSTANCE.userContextToUser(userContext), userRoutineRequestDTO);
    }

    @GetMapping("/{userId}/routines")
    public List<UserRoutineResponseDTO> getUsersRoutines(@PathVariable Integer userId,
                                                         @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToUser(userContext, userId);
        // @TODO - add query param to choose between an 'expanded' vs 'minimized' view of the routines
        return userRoutineService.getUsersRoutinesById(UserContextMapper.INSTANCE.userContextToUser(userContext));
    }

    @GetMapping("/{userId}/routine/selected")
    public UserRoutineResponseDTO getSelectedUserRoutine(@PathVariable Integer userId,
                                                         @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToUser(userContext, userId);
        return userRoutineService.getSelectedUserRoutine(UserContextMapper.INSTANCE.userContextToUser(userContext));
    }
}
