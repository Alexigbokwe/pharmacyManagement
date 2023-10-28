package com.pharmacy.management.pms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pharmacy.management.pms.utils.ErrorResponse;

@ControllerAdvice
public class SystemExceptionHandler {
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ErrorResponse<Throwable> errorResponse = new ErrorResponse<>(resourceNotFoundException.getMessage(),
                resourceNotFoundException.getStatusCode(), resourceNotFoundException.getCause());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(value = { ResourceAlreadyExistException.class })
    public ResponseEntity<Object> handleResourceAlreadyExistException(
            ResourceAlreadyExistException resourceAlreadyExistException) {
        ErrorResponse<Throwable> errorResponse = new ErrorResponse<>(resourceAlreadyExistException.getMessage(),
                resourceAlreadyExistException.getStatusCode(), resourceAlreadyExistException.getCause());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {
        ErrorResponse<Throwable> errorResponse = new ErrorResponse<>(badRequestException.getMessage(),
                badRequestException.getStatusCode(), badRequestException.getCause());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
