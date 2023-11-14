package com.company.organization.mapper.outcome;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OutcomeProductMapper {
    OutcomeProductMapper OUTCOME_PRODUCT_MAPPER = Mappers.getMapper(OutcomeProductMapper.class);


}
