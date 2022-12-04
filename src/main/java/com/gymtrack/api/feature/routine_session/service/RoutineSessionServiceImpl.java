package com.gymtrack.api.feature.routine_session.service;

import com.gymtrack.api.exception.ActiveRoutineSessionException;
import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.exercise.repository.ExerciseRepository;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine_session.dto.RoutineSessionSetRequestDTO;
import com.gymtrack.api.feature.routine_session.session_exercise_log.model.SessionExerciseLog;
import com.gymtrack.api.feature.routine_session.session_exercise_log.repository.SessionExerciseLogRepository;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.routine_session.session_log.repository.SessionLogRepository;
import com.gymtrack.api.feature.set.mapper.SetMapper;
import com.gymtrack.api.feature.set.model.Set;
import com.gymtrack.api.feature.set.repository.SetRepository;
import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RoutineSessionServiceImpl {
    private final ExerciseRepository exerciseRepository;
    private final SessionLogRepository sessionLogRepository;
    private final SessionExerciseLogRepository sessionExerciseLogRepository;
    private final SetRepository setRepository;
    private final UserRepository userRepository;
    private final Clock clock = Clock.systemUTC();

    private boolean existsActiveRoutineSession(Integer userId, Integer routineId) {
        return sessionLogRepository.findActiveRoutineSessionLog(userId, routineId).isPresent();
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

    @Transactional
    public SessionExerciseLog addSetToRoutineSession(Routine routine, Integer userId, RoutineSessionSetRequestDTO routineSessionSetRequestDTO) {
        SessionLog sessionLog = sessionLogRepository.findActiveRoutineSessionLog(userId, routine.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Active routine session was not found for user %d", userId)));

        Exercise exercise = exerciseRepository.findById(routineSessionSetRequestDTO.exerciseId()).orElseThrow(NotFoundException::new);

        SessionExerciseLog sessionExerciseLog;

        if (routineSessionSetRequestDTO.sessionExerciseLogId() != null) {
            // the exercise associated with this set is already added in the current session
            sessionExerciseLog = sessionExerciseLogRepository
                    .findById(routineSessionSetRequestDTO.sessionExerciseLogId())
                    .orElseThrow(NotFoundException::new);
        } else {
            // just finished the first set for this exercise
            sessionExerciseLog = sessionExerciseLogRepository.save(new SessionExerciseLog(exercise, sessionLog));
        }

        Set set = SetMapper.INSTANCE.setRequestDTOToSet(routineSessionSetRequestDTO.set());
        sessionExerciseLog.getSets().add(setRepository.save(set));
        return sessionExerciseLogRepository.save(sessionExerciseLog);
    }
}
