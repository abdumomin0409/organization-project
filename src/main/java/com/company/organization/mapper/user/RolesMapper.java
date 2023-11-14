package com.company.organization.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    RolesMapper ROLES_MAPPER = Mappers.getMapper(RolesMapper.class);



}
