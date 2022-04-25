package com.gymtrack.api.feature.routine.service;

import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;

    @Transactional
    public Routine createRoutine() {
        // create routine
        // set existing selected routine to non-selected
        // add it to app_user_routine and default it to selected
        return null;
    }

    public List<Routine> getRoutines() {
        return routineRepository.findAll();
    }

    public Routine getRoutine(Integer id) {
        return routineRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Routine was not found"));
    }

    public Integer deleteRoutine(Integer id) {
        routineRepository.deleteById(id);
        return id;
    }
}
