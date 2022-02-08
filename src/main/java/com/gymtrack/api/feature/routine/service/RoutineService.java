package com.gymtrack.api.feature.routine.service;

import com.gymtrack.api.feature.routine.model.Routine;

import java.util.List;

public interface RoutineService {
    List<Routine> getRoutines();
    Routine getRoutine(Integer id);
    Integer deleteRoutine(Integer id);
}
