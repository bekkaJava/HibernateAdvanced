package org.hibernate.studentmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    COURSE_NOT_FOUND("course with id: %d not found"),

    INSTRUCTOR_NOT_FOUND("Instructor with id: %d not found"),

    STUDENT_NOT_FOUND("student with id: %d not found");

    private final String message;



}
