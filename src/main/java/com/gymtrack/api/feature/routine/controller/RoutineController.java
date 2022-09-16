package com.gymtrack.api.feature.routine.controller;

import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.exception.AuthenticationException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/routines")
public class RoutineController {
    private final RoutineService routineService;

    //    @GetMapping
    public Page<Routine> getRoutines() {
        int offset = 0; // should pass this through request body eventually
        // this will be part of top 100 routines list w/ pagination
        // user can filter/search on client side, just debounce, and re-fetch with this endpoint
        // (thus will need more advanced request body params)
        return routineService.getRoutines(offset);
    }

    @GetMapping("{id}")
    public Routine getRoutine(@PathVariable Integer id, @RequestAttribute(value = "userContext") UserContext user) {
        Routine routine = routineService.getRoutine(id);
        // @TODO if super-admin, can override + create validation/ACL class/layer
        if (routine.getCreatorId().equals(user.getId())) {
            return routine;
        }

        return null;
    }

    @DeleteMapping("{id}")
    public Integer deleteRoutine(@PathVariable Integer id, @RequestAttribute(value = "userContext") UserContext user) {
        Routine routine = routineService.getRoutine(id);
        // @TODO if super-admin, can override
        if (routine.getCreatorId().equals(user.getId())) {
            throw new AuthenticationException();
        }
        return routineService.deleteRoutine(id);
    }
}
