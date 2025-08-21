package com.htet.employeemanagementapi.exceptions;

import java.io.Serial;

public class ExpiredRefreshTokenExceptions extends BaseException{
    @Serial
    private static final long serialVersionUID = -2571086053053059033L;

    public ExpiredRefreshTokenExceptions(String message) {
        super(message);
    }
}
