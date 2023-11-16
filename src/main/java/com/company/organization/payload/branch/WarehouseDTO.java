package com.company.organization.payload.branch;

public record WarehouseDTO(
        String name,
        String address,
        Long branchId,
        Double longitude,
        Double latitude,
        Long keeperId) {
}
