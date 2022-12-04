package com.gymtrack.api.feature.routine.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
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
    private final AccessControlService accessControlService;

    //    @GetMapping
    public Page<Routine> getRoutines() {
        int offset = 0; // should pass this through request body eventually
        // this will be part of top 100 routines list w/ pagination
        // user can filter/search on client side, just debounce, and re-fetch with this endpoint
        // (thus will need more advanced request body params)
        return routineService.getRoutines(offset);
    }

    @GetMapping("{routineId}")
    public Routine getRoutine(@PathVariable Integer routineId, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, routineId);
        return routineService.getRoutine(routineId);
    }

    @DeleteMapping("{routineId}")
    public Integer deleteRoutine(@PathVariable Integer routineId, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, routineId);
        return routineService.deleteRoutine(routineId);
    }
}
