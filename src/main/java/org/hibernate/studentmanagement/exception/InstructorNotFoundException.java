package org.hibernate.studentmanagement.exception;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(String message) {
        super(message);
    }
}
