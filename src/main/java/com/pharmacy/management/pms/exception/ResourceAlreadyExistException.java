package com.pharmacy.management.pms.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistException extends RuntimeException {
    private HttpStatus statusCode;

    public ResourceAlreadyExistException(String message) {
        super(message);
        this.statusCode = HttpStatus.CONFLICT;
    }
}
