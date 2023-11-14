package com.company.organization.mapper.branch;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseMapper WAREHOUSE_MAPPER = Mappers.getMapper(WarehouseMapper.class);

}
