package com.aurionpro.mappings.exceptions;

public class StudentDoesNotExistException extends RuntimeException {

    public StudentDoesNotExistException(String message) {
        super(message);
    }
}
