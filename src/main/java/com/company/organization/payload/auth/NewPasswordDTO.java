package com.company.organization.payload.auth;

public record NewPasswordDTO(
        String code,
        String password,
        String confirmPassword) {
}
