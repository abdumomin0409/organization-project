package com.company.organization.annotations.annotation;

import com.company.organization.annotations.validation.UniqueNameOrganizationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameOrganizationValidator.class)
public @interface UniqueNameOrganization {
    String message() default "THis name is already registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
