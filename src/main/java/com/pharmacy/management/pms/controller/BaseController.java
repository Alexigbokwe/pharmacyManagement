package com.pharmacy.management.pms.controller;

import com.pharmacy.management.pms.utils.SuccessResponse;

public abstract class BaseController {
    public <T> SuccessResponse<T> successResponse(String message, T data) {
        return new SuccessResponse<>(message, data);
    }
}
