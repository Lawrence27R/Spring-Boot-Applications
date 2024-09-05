package com.aurionpro.bankapp.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.bankapp.dto.ErrorResponsePageDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePageDto> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePageDto> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePageDto> handleIllegalArgumentException(IllegalArgumentException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<ErrorResponsePageDto> buildErrorResponse(HttpStatus status, Map<String, String> errorMessages) {
        ErrorResponsePageDto errorResponse = new ErrorResponsePageDto();
        errorResponse.setStatus(status.value());
        errorResponse.setErrorMessages(errorMessages);
        errorResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponsePageDto> buildErrorResponse(HttpStatus status, String message) {
        Map<String, String> errorMessages = new HashMap<>();
        errorMessages.put("error", message);
        return buildErrorResponse(status, errorMessages);
    }
}
