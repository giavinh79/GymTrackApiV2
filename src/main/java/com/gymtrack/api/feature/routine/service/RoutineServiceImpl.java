package com.gymtrack.api.feature.routine.service;

import com.gymtrack.api.exception.NotFoundException;
import com.gymtrack.api.feature.routine.model.Routine;
import com.gymtrack.api.feature.routine.repository.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;

    public Page<Routine> getRoutines(int offset) {
        Pageable sortedByRating = PageRequest.of(offset, 100, Sort.by("rating"));
        return routineRepository.findAll(sortedByRating);
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
