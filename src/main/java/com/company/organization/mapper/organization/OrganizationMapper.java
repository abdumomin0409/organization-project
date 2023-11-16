package com.company.organization.mapper.organization;

import com.company.organization.domain.organization.Organization;
import com.company.organization.payload.organization.OrganizationCreateDTO;
import com.company.organization.payload.organization.OrganizationUpdateDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationMapper ORGANIZATION_MAPPER = Mappers.getMapper(OrganizationMapper.class);

    Organization fromCreateToOrganization(OrganizationCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void fromUpdateToOrganization(OrganizationUpdateDTO dto, @MappingTarget Organization organization);

}
