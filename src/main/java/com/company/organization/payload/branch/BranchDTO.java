package com.company.organization.payload.branch;

import com.company.organization.payload.BaseDTO;

import java.time.LocalDate;

public record BranchDTO(
        String name,
        String address,
        Long organizationId,
        LocalDate workBegin) implements BaseDTO {
}
