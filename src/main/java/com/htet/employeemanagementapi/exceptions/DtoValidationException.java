package com.htet.employeemanagementapi.exceptions;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@Getter
public class DtoValidationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -386636535032112727L;

    private final Map<String, String> messages = new HashMap<>();

    public DtoValidationException(BindingResult result){
        for (var fieldError : result.getFieldErrors()){
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
