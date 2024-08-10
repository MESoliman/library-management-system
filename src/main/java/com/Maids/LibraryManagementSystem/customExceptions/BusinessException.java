package com.Maids.LibraryManagementSystem.customExceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BusinessException(String message) {
        super(message);
    }
}
