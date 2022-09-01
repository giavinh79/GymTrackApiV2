package com.gymtrack.api.feature.exercise.service;

import com.gymtrack.api.feature.exercise.model.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getPublicExercises();
}
