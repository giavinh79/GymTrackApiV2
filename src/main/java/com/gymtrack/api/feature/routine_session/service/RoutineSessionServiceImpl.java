package com.gymtrack.api.feature.routine_session.service;

import com.gymtrack.api.exception.ActiveRoutineSessionException;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.routine_session.session_log.repository.SessionLogRepository;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoutineSessionServiceImpl {
    private final SessionLogRepository sessionLogRepository;
    private final UserRepository userRepository;
    private final Clock clock = Clock.systemUTC();

    private boolean existsActiveRoutineSession(Integer userId, Integer routineId) {
        Optional<SessionLog> sessionLog = sessionLogRepository.findActiveRoutineSessionLog(userId, routineId);
        return sessionLog.isPresent();
    }

    public void validateNoActiveRoutineSession(Integer userId, Routine routine) {
        if (existsActiveRoutineSession(userId, routine.getId())) {
            throw new ActiveRoutineSessionException();
        }
    }

    public SessionLog startRoutineSession(Routine routine, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User does not exist"));

        SessionLog newRoutineSessionLog = SessionLog
                .builder()
                .routine(routine)
                .user(user)
                .startTime(LocalDateTime.now(clock))
                .build();

        return sessionLogRepository.save(newRoutineSessionLog);
    }

    public SessionLog stopRoutineSession(Routine routine, Integer userId) {
        SessionLog sessionLog = sessionLogRepository.findActiveRoutineSessionLog(userId, routine.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Active routine session was not found for user %d", userId)));

        sessionLog.setEndTime(LocalDateTime.now(clock));
        return sessionLogRepository.save(sessionLog);
    }
}
