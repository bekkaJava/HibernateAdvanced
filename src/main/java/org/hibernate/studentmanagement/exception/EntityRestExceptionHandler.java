package org.hibernate.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityRestExceptionHandler {


    @ExceptionHandler(InstructorNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> instructorNotFound(InstructorNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                                            e.getMessage(),
                                            HttpStatus.NOT_FOUND,
                                            System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }



    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> instructorNotFound(CourseNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> instructorNotFound(StudentNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<EntityErrorResponse> instructorNotFound(StudentAlreadyExistsException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<EntityErrorResponse> instructorNotFound(CourseAlreadyExistsException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<EntityErrorResponse> instructorNotFound(Exception e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
