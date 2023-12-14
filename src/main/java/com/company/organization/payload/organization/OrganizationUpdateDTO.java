package com.company.organization.payload.organization;

import com.company.organization.payload.BaseDTO;
import jakarta.validation.constraints.NotBlank;

public record OrganizationUpdateDTO(
        @NotBlank(message = "Organization name should not be null or empty") String name,
        String address,
        String phoneNumber,
        String email) implements BaseDTO {
}
