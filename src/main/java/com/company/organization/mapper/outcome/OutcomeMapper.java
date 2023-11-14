package com.company.organization.mapper.outcome;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OutcomeMapper {
    OutcomeMapper OUTCOME_MAPPER = Mappers.getMapper(OutcomeMapper.class);
}
