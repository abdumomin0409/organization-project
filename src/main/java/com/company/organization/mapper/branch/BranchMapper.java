package com.company.organization.mapper.branch;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchMapper BRANCH_MAPPER = Mappers.getMapper(BranchMapper.class);


}
