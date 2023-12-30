package com.example.employeeservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ServiceUnavailableException extends RuntimeException {
    private final HttpStatusCode serviceReturnCode;

    public ServiceUnavailableException(String message, HttpStatusCode serviceReturnCode) {
        super(message);
        this.serviceReturnCode = serviceReturnCode;
    }
}
