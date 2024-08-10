package com.Maids.LibraryManagementSystem.dtos;

public class ErrorResponseDto {
    private final int statusCode;
    private final String statusMsg;

    public ErrorResponseDto(int statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}
