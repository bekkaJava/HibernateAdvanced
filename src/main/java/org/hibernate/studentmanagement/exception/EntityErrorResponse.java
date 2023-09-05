package org.hibernate.studentmanagement.exception;

import org.springframework.http.HttpStatus;

public record EntityErrorResponse(
        String message,
        HttpStatus httpStatus,
        long timestamp) {

}
