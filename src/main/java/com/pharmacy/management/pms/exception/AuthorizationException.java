package com.pharmacy.management.pms.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {
    private HttpStatus statusCode;

    public AuthorizationException() {
        super("Not authorized to perform action");
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }
}
