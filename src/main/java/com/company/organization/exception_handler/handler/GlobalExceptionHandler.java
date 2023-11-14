package com.company.organization.exception_handler.handler;


import com.company.organization.exception_handler.exception.ValidationException;
import com.company.organization.response.ResponseData;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.webjars.NotFoundException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseData<?>> notFoundException(NotFoundException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().code(-1515515).success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResponseData<?>> notFoundException(ValidationException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).code(ex.getCode()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ResponseData<?>> notFoundException(NullPointerException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().code(-151518).success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }


    @ExceptionHandler({io.micrometer.core.instrument.config.validate.ValidationException.class})
    public ResponseEntity<ResponseData<?>> validationException(io.micrometer.core.instrument.config.validate.ValidationException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().code(-121212).success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({Error.class})
    public ResponseEntity<ResponseData<?>> userAlreadyTakenException(Error ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(RuntimeException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({CredentialsExpiredException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(CredentialsExpiredException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({InsufficientAuthenticationException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(InsufficientAuthenticationException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(ExpiredJwtException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(DataIntegrityViolationException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(DataAccessException ex, WebRequest request) {
        ResponseData<Object> path = ResponseData.builder().success(false).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(path);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseData<?>> userNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorBody.compute(field, (s, values) -> {
                if (!Objects.isNull(values))
                    values.add(message);
                else
                    values = new ArrayList<>(Collections.singleton(message));
                return values;
            });
        }
        ResponseData<Object> path = ResponseData.builder().success(false).data(errorBody)
                .build();
        return ResponseEntity.badRequest().body(path);
    }


}
