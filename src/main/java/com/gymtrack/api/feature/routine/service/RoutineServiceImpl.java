package com.gymtrack.api.feature.routine.service;

import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;

    public List<Routine> getRoutines() {
        Iterable<Routine> routines = routineRepository.findAll();

        return StreamSupport.stream(routines.spliterator(), false).toList();
    }

    public Routine getRoutine(Integer id) {
        return routineRepository.findById(id).orElseThrow();
    }

    public Integer deleteRoutine(Integer id) {
        routineRepository.deleteById(id);
        return id;
    }
}
