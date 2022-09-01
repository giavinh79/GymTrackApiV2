package com.gymtrack.api.feature.exercise.mapper;

import com.gymtrack.api.feature.exercise.dto.ExerciseDTO;
import com.gymtrack.api.feature.exercise.model.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    ExerciseDTO routineToRoutineDTO(Exercise routine);
}