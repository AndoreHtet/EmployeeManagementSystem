package com.htet.employeemanagementapi.util.handler;

import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import org.springframework.validation.BindingResult;

public class BindingResultHandler {

    public static void checkBindingResultError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
    }
}
