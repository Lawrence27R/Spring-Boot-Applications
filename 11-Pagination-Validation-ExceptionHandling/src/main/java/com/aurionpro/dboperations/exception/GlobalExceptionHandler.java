package com.aurionpro.dboperations.exception;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.dboperations.errors.ErrorResponsePage;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponsePage> handleCustomerDoesNotExistException(CustomerDoesNotExistException exception) {
        ErrorResponsePage error = new ErrorResponsePage();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setErrorMessages(Map.of("error", exception.getMessage()));
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponsePage> handleLoanDoesNotExistException(LoanDoesNotExistException exception) {
        ErrorResponsePage error = new ErrorResponsePage();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setErrorMessages(Map.of("error", exception.getMessage()));
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponsePage> handlePaymentDoesNotExistException(PaymentDoesNotExistException exception) {
        ErrorResponsePage error = new ErrorResponsePage();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setErrorMessages(Map.of("error", exception.getMessage()));
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponsePage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ErrorResponsePage error = new ErrorResponsePage();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessages(Map.of("error", "Invalid Search. Search by Id only."));
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponsePage> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        ErrorResponsePage error = new ErrorResponsePage();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessages(errors);
        error.setTimeStamp(new Date());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
