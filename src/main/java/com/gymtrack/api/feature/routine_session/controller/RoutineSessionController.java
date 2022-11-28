package com.gymtrack.api.feature.routine_session.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine_session.service.RoutineSessionServiceImpl;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/routine/")
public class RoutineSessionController {
    private final RoutineSessionServiceImpl routineSessionService;
    private final AccessControlService accessControlService;

    @PostMapping("/{routineId}/session/start")
    public SessionLog startRoutineSession(@PathVariable Integer routineId, @RequestAttribute(value = "userContext") UserContext userContext) {
        Routine routine = accessControlService.validateAccessToRoutine(userContext, userContext.getId(), routineId);
        routineSessionService.validateNoActiveRoutineSession(userContext.getId(), routine);
        return routineSessionService.startRoutineSession(routine, userContext.getId());
    }

    @PostMapping("/{routineId}/session/end")
    public SessionLog endRoutineSession(@PathVariable Integer routineId, @RequestAttribute(value = "userContext") UserContext userContext) {
        Routine routine = accessControlService.validateAccessToRoutine(userContext, userContext.getId(), routineId);
        return routineSessionService.stopRoutineSession(routine, userContext.getId());
    }
}

