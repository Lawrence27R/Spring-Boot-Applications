package com.aurionpro.dboperations.exception;

public class StudentDoesNotExistException extends RuntimeException {

    public StudentDoesNotExistException(int rollNumber) {
        super("Student with roll number " + rollNumber + " cannot be found.");
    }
}
