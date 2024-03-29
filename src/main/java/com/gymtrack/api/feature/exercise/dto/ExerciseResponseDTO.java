package com.gymtrack.api.feature.exercise.dto;

import com.gymtrack.api.feature.exercise_value_type.model.ExerciseValueType;
import com.gymtrack.api.feature.image.dto.ImageResponseDTO;
import com.gymtrack.api.feature.muscle.model.Muscle;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record ExerciseResponseDTO(Integer id, String name, String description, Integer creatorId, Integer imageId,
                                  ImageResponseDTO image, List<Muscle> musclesUsed,
                                  ExerciseValueType exerciseValueType) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1490275200845737305L;
}
