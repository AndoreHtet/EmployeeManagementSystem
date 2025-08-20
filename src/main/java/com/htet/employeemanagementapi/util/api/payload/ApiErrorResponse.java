package com.htet.employeemanagementapi.util.api.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
