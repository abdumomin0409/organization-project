package com.company.organization.payload.organization;

import jakarta.validation.constraints.NotBlank;

public record ProductDTO(
        @NotBlank(message = "Product name should not be null or empty") String name,
        String description) {
}
