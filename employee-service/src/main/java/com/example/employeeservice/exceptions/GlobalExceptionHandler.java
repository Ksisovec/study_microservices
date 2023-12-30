package com.example.employeeservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<Object>> resourceNotFoundException(ResourceNotFoundException ex,
                                                                  WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "RESOURCE_NOT_FOUND"
        );
        return createResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public Mono<ResponseEntity<Object>> emailAlreadyExistsException(EmailAlreadyExistException ex,
                                                                    ServerWebExchange exchange) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                "EMAIL_ALREADY_EXISTS"
        );
        return createResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Object>> handleGlobalException(Exception ex,
                                                              ServerWebExchange exchange) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                "INTERNAL_SERVER_ERROR"
        );
        return createResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public Mono<ResponseEntity<Object>> serviceUnavailableException(ServiceUnavailableException ex,
                                                                    ServerWebExchange exchange) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                "INTERNAL_SERVER_ERROR"
        );
        return createResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExternalResourceNotFoundException.class)
    public Mono<ResponseEntity<Object>> externalResourceNotFoundException(ExternalResourceNotFoundException ex,
                                                                          ServerWebExchange exchange) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                "INTERNAL_SERVER_ERROR"
        );
        return createResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected Mono<ResponseEntity<Object>> handleServerWebInputException(
            ServerWebInputException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Parameter " + ex.getMethodParameter().getParameterName() + " is incorrect",
                exchange.getRequest().getPath().value(),
                "BAD_REQUEST"
        );
        return createResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers,
//                                                                  HttpStatusCode status,
//                                                                  WebRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
//        errorList.forEach(er -> {
//            String fieldName = ((FieldError) er).getField();
//            String message = er.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

    protected Mono<ResponseEntity<Object>> createResponseEntity(@NonNull ErrorDetails errorDetails, @NonNull HttpStatus httpStatus) {
        return Mono.just(new ResponseEntity<>(errorDetails, httpStatus));
    }
}
