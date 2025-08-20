package com.htet.employeemanagementapi.exceptions;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 3081743035434873349L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String template, Object arg1) {
        this(format(template, arg1));
    }

    public CustomException(String template, Object arg1, Throwable cause) {
        this(format(template, arg1), cause);
    }

    public CustomException(String template, Object arg1, Object arg2) {
        this(format(template, arg1, arg2));
    }

    public CustomException(String template, Object arg1, Object arg2, Throwable cause) {
        this(format(template, arg1, arg2), cause);
    }

    public static class AlreadyExists extends CustomException{
        public AlreadyExists(String template, Object arg){
            super(format(template,arg));
        }
    }

    public static class NotFound extends CustomException{
        public NotFound(String message){
            super(message);
        }
    }


}
