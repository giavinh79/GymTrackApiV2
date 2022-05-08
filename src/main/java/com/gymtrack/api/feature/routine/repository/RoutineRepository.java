package com.gymtrack.api.feature.routine.repository;

import com.gymtrack.api.feature.routine.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {
}
