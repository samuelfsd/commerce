package com.samuelfsd.br.commerce.controllers.handlers;

import com.samuelfsd.br.commerce.exceptions.CustomError;
import com.samuelfsd.br.commerce.exceptions.CustomValidationError;
import com.samuelfsd.br.commerce.exceptions.DatabaseException;
import com.samuelfsd.br.commerce.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> resourceValidationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status  = HttpStatus.BAD_REQUEST;
        CustomValidationError err = new CustomValidationError(Instant.now(), status.value(), "Dados inválidos.", request.getRequestURI());

        e.getBindingResult().getFieldErrors().forEach(fieldError ->
            err.addError(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status.value()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> internalError(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        System.out.println(e.getMessage());
        CustomError error = new CustomError(
                Instant.now(),
                status.value(),
                "Ocorreu um erro inesperado. Tente novamente mais tarde!",
                request.getRequestURI());

        return ResponseEntity.status(status.value()).body(error);
    }
}
