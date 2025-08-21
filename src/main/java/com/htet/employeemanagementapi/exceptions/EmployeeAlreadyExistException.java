package com.htet.employeemanagementapi.exceptions;

import java.io.Serial;

public class EmployeeAlreadyExistException extends BaseException{
    @Serial
    private static final long serialVersionUID = -2571086053053059033L;

    public EmployeeAlreadyExistException(String message) {
        super(message);
    }
}
