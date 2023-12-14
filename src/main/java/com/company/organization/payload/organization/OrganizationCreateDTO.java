package com.company.organization.payload.organization;

import com.company.organization.annotations.annotation.UniqueNameOrganization;
import com.company.organization.payload.BaseDTO;
import jakarta.validation.constraints.NotBlank;

public record OrganizationCreateDTO(
        @NotBlank(message = "Organization name should not be null or empty") @UniqueNameOrganization String name,
        String address,
        String phoneNumber,
        String email) implements BaseDTO {
}
