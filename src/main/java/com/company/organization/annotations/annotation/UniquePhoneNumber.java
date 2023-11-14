package com.company.organization.annotations.annotation;

import com.company.organization.annotations.validation.UniqueNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNumberValidator.class)
public @interface UniquePhoneNumber {
    String message() default "Phone Number is already registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
