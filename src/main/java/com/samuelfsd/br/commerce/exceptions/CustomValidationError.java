package com.samuelfsd.br.commerce.exceptions;

import com.samuelfsd.br.commerce.dtos.validation.FieldMessagesDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomValidationError extends CustomError{

    private List<FieldMessagesDTO> errors = new ArrayList<>();
    public CustomValidationError(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public List<FieldMessagesDTO> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessagesDTO(fieldName, message));
    }

}
