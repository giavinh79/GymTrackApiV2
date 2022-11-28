package com.gymtrack.api.feature.routine_session.session_log.controller;

import com.gymtrack.api.acl.AccessControlService;
import com.gymtrack.api.context.UserContext;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.routine_session.session_log.service.SessionLogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/")
public class SessionLogController {
    private final SessionLogServiceImpl sessionLogService;
    private final AccessControlService accessControlService;

    @GetMapping("/routine/{routineId}/session-logs")
    public List<SessionLog> getRoutineSessionLogs(@PathVariable Integer routineId, @RequestAttribute(value = "userContext") UserContext userContext) {
        accessControlService.validateAccessToRoutine(userContext, userContext.getId(), routineId);
        return sessionLogService.getRoutineSessionLogs(userContext.getId(), routineId);
    }
}
