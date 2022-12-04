package com.gymtrack.api.feature.routine_exercise.dto;

import com.gymtrack.api.feature.image.dto.ImageResponseDTO;
import com.gymtrack.api.feature.muscle.model.Muscle;
import com.gymtrack.api.feature.set.dto.SetResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RoutineExerciseResponseDTO {
    Integer id;
    Integer exerciseOrder;
    String name;
    String description;
    Integer creatorId;
    List<SetResponseDTO> sets;
    List<Muscle> musclesUsed;
    ImageResponseDTO image;
}
