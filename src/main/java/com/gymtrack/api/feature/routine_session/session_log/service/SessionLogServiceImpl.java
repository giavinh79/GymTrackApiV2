package com.gymtrack.api.feature.routine_session.session_log.service;

import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.routine_session.session_log.repository.SessionLogRepository;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SessionLogServiceImpl {
    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;
    private final SessionLogRepository sessionLogRepository;

    public List<SessionLog> getRoutineSessionLogs(Integer userId, Integer routineId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User does not exist"));

        Routine routine = routineRepository
                .findById(routineId)
                .orElseThrow(() -> new NotFoundException("Routine was not found"));

        return sessionLogRepository.findAllByUserEqualsAndRoutineEquals(user, routine);
    }
}
