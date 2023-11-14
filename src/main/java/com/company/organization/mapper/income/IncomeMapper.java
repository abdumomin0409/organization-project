package com.company.organization.mapper.income;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    IncomeMapper INCOME_MAPPER = Mappers.getMapper(IncomeMapper.class);
}
