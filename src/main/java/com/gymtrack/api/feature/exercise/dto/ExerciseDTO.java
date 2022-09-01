package com.gymtrack.api.feature.exercise.dto;

public record ExerciseDTO(Integer id, String name, String description, Integer defaultExerciseValueType,
                          Integer creatorId, Integer imageId) {
}
