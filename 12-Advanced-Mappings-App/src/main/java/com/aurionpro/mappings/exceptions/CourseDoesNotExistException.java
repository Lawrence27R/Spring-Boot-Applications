package com.aurionpro.mappings.exceptions;

public class CourseDoesNotExistException extends RuntimeException {

    public CourseDoesNotExistException(String message) {
        super(message);
    }
}
