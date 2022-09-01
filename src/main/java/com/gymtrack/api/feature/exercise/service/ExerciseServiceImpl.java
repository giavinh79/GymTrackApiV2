package com.gymtrack.api.feature.exercise.service;

import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.exercise.repository.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> getPublicExercises() {
        return exerciseRepository.findExercisesByCreatorIdIsNull();
    }
}
