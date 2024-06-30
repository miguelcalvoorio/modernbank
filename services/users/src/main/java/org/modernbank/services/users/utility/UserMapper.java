package org.modernbank.services.users.utility;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import org.modernbank.services.users.client.UserRest;
import org.modernbank.services.users.model.User;
import org.keycloak.representations.idm.UserRepresentation;


@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    UserRest toRestData(UserRepresentation userRepresentation);

    @Mapping(source = "id", target = "id")
    UserRest toRestData(User user);

    @Mapping(source = "id", target = "id")
    User toModelFromUserRepresentation(UserRepresentation userRepresentation);
}
