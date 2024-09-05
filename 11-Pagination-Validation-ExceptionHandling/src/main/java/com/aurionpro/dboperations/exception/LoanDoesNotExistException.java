package com.aurionpro.dboperations.exception;

public class LoanDoesNotExistException extends RuntimeException {

    public LoanDoesNotExistException(String message) {
        super(message);
    }
}
