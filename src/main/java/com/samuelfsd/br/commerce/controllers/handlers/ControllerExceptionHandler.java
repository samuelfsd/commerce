package com.samuelfsd.br.commerce.controllers.handlers;

import com.samuelfsd.br.commerce.exceptions.CustomError;
import com.samuelfsd.br.commerce.exceptions.DatabaseException;
import com.samuelfsd.br.commerce.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> resourceDatabase(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status.value()).body(error);
    }
}
