package com.gymtrack.api.feature.routine_session.session_log.repository;

import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine_session.session_log.model.SessionLog;
import com.gymtrack.api.feature.user.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionLogRepository extends JpaRepository<SessionLog, Integer> {
    List<SessionLog> findAllByUserEqualsAndRoutineEquals(User user, Routine routine);

    @Query(value = "SELECT * FROM session_log WHERE routine_id = :routineId AND app_user_id = :userId AND end_time IS NULL LIMIT 1", nativeQuery = true)
    Optional<SessionLog> findActiveRoutineSessionLog(@Param("userId") Integer userId, @Param("routineId") Integer routineId);
}
