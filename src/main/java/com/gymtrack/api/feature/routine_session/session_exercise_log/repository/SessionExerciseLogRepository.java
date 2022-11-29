package com.gymtrack.api.feature.routine_session.session_exercise_log.repository;

import com.gymtrack.api.feature.routine_session.session_exercise_log.model.SessionExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionExerciseLogRepository extends JpaRepository<SessionExerciseLog, Integer> {
}
