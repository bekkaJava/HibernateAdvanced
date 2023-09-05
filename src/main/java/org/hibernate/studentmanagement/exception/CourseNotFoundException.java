package org.hibernate.studentmanagement.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message) {
        super(message);
    }
}
