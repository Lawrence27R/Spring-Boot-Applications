package com.aurionpro.mappings.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.mappings.errors.ErrorResponsePage;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePage> handleCourseNotFoundException(CourseDoesNotExistException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePage> handleInstructorNotFoundException(InstructorDoesNotExistException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePage> handleStudentNotFoundException(StudentDoesNotExistException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePage> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponsePage> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }

        return buildErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    private ResponseEntity<ErrorResponsePage> buildErrorResponse(HttpStatus status, Map<String, String> errorMessages) {
        ErrorResponsePage errorResponse = new ErrorResponsePage();
        errorResponse.setStatus(status.value());
        errorResponse.setErrorMessages(errorMessages);
        errorResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponsePage> buildErrorResponse(HttpStatus status, String message) {
        Map<String, String> errorMessages = new HashMap<>();
        errorMessages.put("error", message);
        return buildErrorResponse(status, errorMessages);
    }
}
