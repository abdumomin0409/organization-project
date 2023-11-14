package com.company.organization.mapper.organization;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationMapper ORGANIZATION_MAPPER = Mappers.getMapper(OrganizationMapper.class);

}
