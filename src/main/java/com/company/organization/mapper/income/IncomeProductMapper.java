package com.company.organization.mapper.income;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IncomeProductMapper {
    IncomeProductMapper INCOME_PRODUCT_MAPPER = Mappers.getMapper(IncomeProductMapper.class);

}
