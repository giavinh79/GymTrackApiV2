package com.gymtrack.api.feature.routine_exercise.mapper;

import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine_exercise.dto.RoutineExerciseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoutineExerciseMapper {
    RoutineExerciseMapper INSTANCE = Mappers.getMapper(RoutineExerciseMapper.class);

    RoutineExerciseResponseDTO exerciseToRoutineExerciseResponseDTO(Exercise exercise);
}
