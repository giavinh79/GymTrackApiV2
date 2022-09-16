package com.gymtrack.api.feature.routine.service;

import com.gymtrack.api.feature.routine.model.Routine;
import org.springframework.data.domain.Page;

public interface RoutineService {
    Page<Routine> getRoutines(int offset);

    Routine getRoutine(Integer id);

    Integer deleteRoutine(Integer id);
}
