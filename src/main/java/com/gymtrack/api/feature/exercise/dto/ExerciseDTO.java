package com.gymtrack.api.feature.exercise.dto;

import com.gymtrack.api.feature.exercise_value_type.model.ExerciseValueType;
import com.gymtrack.api.feature.muscle.model.Muscle;

import java.util.List;

public record ExerciseDTO(Integer id, String name, String description, ExerciseValueType exerciseValueType,
                          Integer creatorId, Integer imageId, List<Muscle> musclesUsed) {
}
