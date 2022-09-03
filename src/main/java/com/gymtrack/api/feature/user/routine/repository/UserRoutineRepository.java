package com.gymtrack.api.feature.user.routine.repository;

import com.gymtrack.api.feature.user.model.User;
import com.gymtrack.api.feature.user_routine.model.UserRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoutineRepository extends JpaRepository<UserRoutine, Integer> {
    List<UserRoutine> findAllByUserEquals(User user);

    UserRoutine findUserRoutineByUserEqualsAndIsSelectedTrue(User user);
}
