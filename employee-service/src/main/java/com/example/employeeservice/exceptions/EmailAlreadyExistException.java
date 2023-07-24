package com.example.employeeservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
