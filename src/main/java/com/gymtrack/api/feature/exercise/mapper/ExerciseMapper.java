package com.gymtrack.api.feature.exercise.mapper;

import com.gymtrack.api.feature.exercise.dto.ExerciseResponseDTO;
import com.gymtrack.api.feature.exercise.model.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    @Mapping(target = "imageId", ignore = true)
    ExerciseResponseDTO exerciseToExerciseDTO(Exercise exercise);
}