package com.company.organization.payload.branch;

import java.time.LocalDate;

public record BranchDTO(
        String name,
        String address,
        Long organizationId,
        LocalDate workBegin) {
}
