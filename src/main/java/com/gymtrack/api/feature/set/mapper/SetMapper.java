package com.gymtrack.api.feature.set.mapper;

import com.gymtrack.api.feature.set.dto.SetRequestDTO;
import com.gymtrack.api.feature.set.model.Set;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SetMapper {
    com.gymtrack.api.feature.set.mapper.SetMapper INSTANCE = Mappers.getMapper(com.gymtrack.api.feature.set.mapper.SetMapper.class);

    Set setRequestDTOToSet(SetRequestDTO setRequestDTO);
}