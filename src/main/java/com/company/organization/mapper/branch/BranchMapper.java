package com.company.organization.mapper.branch;

import com.company.organization.domain.branch.Branch;
import com.company.organization.payload.branch.BranchDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchMapper BRANCH_MAPPER = Mappers.getMapper(BranchMapper.class);

    Branch fromCreateToBranch(BranchDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void fromUpdateToBranch(BranchDTO dto, @MappingTarget Branch warehouse);

}
