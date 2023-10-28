package com.pharmacy.management.pms.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {
    private String message;
    private boolean status;
    private T data;

    public SuccessResponse(String message, T data) {
        this.message = message;
        this.data = data;
        this.status = true;
    }
}
