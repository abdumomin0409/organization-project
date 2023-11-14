package com.company.organization.exception_handler.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException{
    private final Integer code;
    public ValidationException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
