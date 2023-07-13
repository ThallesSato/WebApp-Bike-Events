package com.example.bikeevents.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomExceptions.NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(CustomExceptions.NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Not found");
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(CustomExceptions.BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Bad Request");
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.OwnershipException.class)
    public ResponseEntity<ErrorResponse> handleOwnershipException(CustomExceptions.OwnershipException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Forbidden");
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(CustomExceptions.AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationExceptionException(CustomExceptions.AuthenticationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Unauthorized");
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}

