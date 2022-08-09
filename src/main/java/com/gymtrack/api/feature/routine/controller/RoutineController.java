package com.gymtrack.api.feature.routine.controller;

import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.service.RoutineService;
import com.gymtrack.api.feature.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/routines")
public class RoutineController {
    private final RoutineService routineService;

    //    @GetMapping
    public List<Routine> getRoutines() {
        return routineService.getRoutines();
    }

    @GetMapping("{id}")
    public Routine getRoutine(@PathVariable Integer id, @RequestAttribute(value = "user") User user) {
        Routine routine = routineService.getRoutine(id);
        // @TODO if super-admin, can override + create validation/ACL class/layer
        if (routine.getCreatorId().equals(user.getId())) {
            return routine;
        }

        return null;
    }

    @DeleteMapping("{id}")
    public Integer deleteRoutine(@PathVariable Integer id, @RequestAttribute(value = "user") User user) {
        Routine routine = routineService.getRoutine(id);
        // @TODO if super-admin, can override
        if (routine.getCreatorId().equals(user.getId())) {
            throw new AuthenticationException();
        }
        return routineService.deleteRoutine(id);
    }
}
