package com.gymtrack.api.feature.routine.mapper;

import com.gymtrack.api.feature.routine.dto.RoutineDTO;
import com.gymtrack.api.feature.routine.model.Routine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoutineMapper {
    RoutineMapper INSTANCE = Mappers.getMapper(RoutineMapper.class);

    RoutineDTO routineToRoutineDTO(Routine routine);
}