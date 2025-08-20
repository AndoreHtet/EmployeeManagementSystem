package com.htet.employeemanagementapi.config;

import com.htet.employeemanagementapi.exceptions.CustomException;
import com.htet.employeemanagementapi.util.api.payload.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {



    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBadRequestException(HttpServletRequest req, BadRequestException exception) {
        String url = req.getRequestURL().toString();
        log.error("Request: {} raised following exception.", url, exception);
        return createResponse(url, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMethodArgumentTypeMismatchException(HttpServletRequest req, MethodArgumentTypeMismatchException exception) {
        String url = req.getRequestURL().toString();
        log.error("Request: {} raised following exception.", url, exception);
        return createResponse(url, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleException(HttpServletRequest req, Exception exception) {
        String url = req.getRequestURL().toString();
        log.error("Request: {} raised following exception.", url, exception);
        return createResponse(url, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(CustomException.NotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFoundException(HttpServletRequest req, CustomException.NotFound exception) {
        String url = req.getRequestURL().toString();
        log.error("Request: {} raised following exception.", url, exception);
        return createResponse(url, HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(CustomException.AlreadyExists.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiErrorResponse handleAlreadyExistsException(HttpServletRequest req, CustomException.AlreadyExists exception) {
        String url = req.getRequestURL().toString();
        log.error("Request: {} raised following exception.", url, exception);
        return createResponse(url, HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
    }


    public static ApiErrorResponse createResponse(String url, HttpStatus status, String message){
        ApiErrorResponse response = new ApiErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());
        response.setMessage(message);
        response.setPath(url);
        return response;
    }

}
