package com.gymtrack.api.feature.exercise.controller;

import com.gymtrack.api.feature.exercise.dto.ExerciseResponseDTO;
import com.gymtrack.api.feature.exercise.mapper.ExerciseMapper;
import com.gymtrack.api.feature.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {
    private final ExerciseRepository exerciseRepository;

    @Cacheable(value = "exercises")
    @GetMapping
    public List<ExerciseResponseDTO> getExercises() {
        return exerciseRepository
                .findAll()
                .stream()
                .map(ExerciseMapper.INSTANCE::exerciseToExerciseDTO)
                .toList();
    }
}
