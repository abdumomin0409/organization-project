package com.company.organization.payload.auth;

import jakarta.validation.constraints.NotBlank;

public record TokenRequest(@NotBlank String phoneNumber, @NotBlank String password) {
}
