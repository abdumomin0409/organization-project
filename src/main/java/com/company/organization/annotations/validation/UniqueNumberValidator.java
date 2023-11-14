package com.company.organization.annotations.validation;

import com.company.organization.annotations.annotation.UniquePhoneNumber;
import com.company.organization.service.auth.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    private final AuthService authUserService;

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !authUserService.existsByPhoneNumber(s);
    }
}
