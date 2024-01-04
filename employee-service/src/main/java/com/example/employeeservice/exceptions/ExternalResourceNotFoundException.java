package com.example.employeeservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ExternalResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private HttpStatusCode httpStatusCode;

    public ExternalResourceNotFoundException(String message, String resourceName, HttpStatusCode httpStatusCode) {
        super(message);
        this.resourceName = resourceName;
        this.httpStatusCode = httpStatusCode;
    }
}
