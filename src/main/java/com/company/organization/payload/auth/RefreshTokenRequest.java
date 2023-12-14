package com.company.organization.payload.auth;

import com.company.organization.payload.BaseDTO;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(@NotBlank String refreshToken) implements BaseDTO {}
