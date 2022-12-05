package com.gymtrack.api.feature.routine_exercise.mapper;

import com.gymtrack.api.feature.exercise.model.Exercise;
import com.gymtrack.api.feature.routine_exercise.dto.RoutineExerciseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoutineExerciseMapper {
    RoutineExerciseMapper INSTANCE = Mappers.getMapper(RoutineExerciseMapper.class);

    @Mapping(target = "exerciseOrder", ignore = true)
    @Mapping(target = "sets", ignore = true)
    RoutineExerciseResponseDTO exerciseToRoutineExerciseResponseDTO(Exercise exercise);
}
