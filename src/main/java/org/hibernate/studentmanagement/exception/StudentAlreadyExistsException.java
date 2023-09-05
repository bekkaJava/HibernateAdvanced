package org.hibernate.studentmanagement.exception;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
