package com.pharmacy.management.pms.utils;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> {
    private String message;
    private boolean status;
    private HttpStatusCode statusCode;
    private Optional<T> data;

    public ErrorResponse(String message, HttpStatusCode statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = Optional.ofNullable(data);
        this.status = false;
    }
}
