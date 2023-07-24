package net.javaguides.departmentservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartmentAlreadyExistException extends RuntimeException {
    public DepartmentAlreadyExistException(String message) {
        super(message);
    }
}
