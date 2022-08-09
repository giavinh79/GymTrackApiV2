package com.gymtrack.api.feature.user.routine.controller;

import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.routine.dto.UserRoutineDTO;
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

    @GetMapping("user/{userId}/routines")
    public List<UserRoutineDTO> getUsersRoutines(@PathVariable Integer userId, @RequestAttribute(value = "user") User user) {
        if (user.getId().equals(userId)) {
            throw new AuthenticationException("Unauthorized");
        }

        return userRoutineService.getUsersRoutinesById(userId);
    }
}
