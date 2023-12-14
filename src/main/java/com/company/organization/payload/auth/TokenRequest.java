package com.company.organization.payload.auth;

import com.company.organization.payload.BaseDTO;
import jakarta.validation.constraints.NotBlank;

public record TokenRequest(@NotBlank String phoneNumber, @NotBlank String password) implements BaseDTO {
}
