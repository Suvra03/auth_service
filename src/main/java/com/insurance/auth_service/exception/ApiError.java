package com.insurance.auth_service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus status;

    public ApiError(String message, HttpStatus status) {
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
    }
}
