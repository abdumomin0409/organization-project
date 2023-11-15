package com.company.organization.annotations.validation;

import com.company.organization.annotations.annotation.UniqueNameOrganization;
import com.company.organization.service.organization.OrganizationService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UniqueNameOrganizationValidator implements ConstraintValidator<UniqueNameOrganization, String> {
    private final OrganizationService organizationService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.nonNull(value))
            return !organizationService.existsByName(value);
        return false;
    }

    @Override
    public void initialize(UniqueNameOrganization constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
