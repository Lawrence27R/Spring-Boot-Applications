package com.aurionpro.dboperations.exception;

public class PaymentDoesNotExistException extends RuntimeException {

    public PaymentDoesNotExistException(String message) {
        super(message);
    }
}
