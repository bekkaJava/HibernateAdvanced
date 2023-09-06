package org.hibernate.studentmanagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class EntityRestExceptionHandler {


    @ExceptionHandler(InstructorNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> instructorNotFound(InstructorNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);

    }


    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> courseNotFound(CourseNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);

    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> studentNotFound(StudentNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);

    }


    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<EntityErrorResponse> studentAlreadyExists(StudentAlreadyExistsException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, BAD_REQUEST);

    }


    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<EntityErrorResponse> courseAlreadyExists(CourseAlreadyExistsException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<EntityErrorResponse> exception(Exception e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                INTERNAL_SERVER_ERROR,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);

    }

}
