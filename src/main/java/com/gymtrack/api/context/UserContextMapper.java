package com.gymtrack.api.context;

import com.gymtrack.api.feature.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserContextMapper {
    UserContextMapper INSTANCE = Mappers.getMapper(UserContextMapper.class);

    UserContext userToUserContext(User user);

    User userContextToUser(UserContext userContext);
}