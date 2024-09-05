package com.aurionpro.dboperations.exception;

public class CustomerDoesNotExistException extends RuntimeException {

    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}
