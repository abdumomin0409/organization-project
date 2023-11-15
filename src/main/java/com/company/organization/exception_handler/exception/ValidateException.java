package com.company.organization.exception_handler.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateException extends RuntimeException{
    private final Integer code;
    public ValidateException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
