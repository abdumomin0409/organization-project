package com.company.organization.mapper.organization;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationProductMapper {
    OrganizationProductMapper ORGANIZATION_PRODUCT_MAPPER = Mappers.getMapper(OrganizationProductMapper.class);
}
