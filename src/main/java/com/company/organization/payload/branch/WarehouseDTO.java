package com.company.organization.payload.branch;

import com.company.organization.payload.BaseDTO;

public record WarehouseDTO(
        String name,
        String address,
        Long branchId,
        Double longitude,
        Double latitude,
        Long keeperId) implements BaseDTO {
}
