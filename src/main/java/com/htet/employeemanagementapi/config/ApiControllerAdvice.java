package com.htet.employeemanagementapi.config;

import com.htet.employeemanagementapi.exceptions.CustomException;
import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import com.htet.employeemanagementapi.exceptions.ExpiredRefreshTokenException;
import com.htet.employeemanagementapi.exceptions.WrongRefreshTokenException;
import com.htet.employeemanagementapi.util.api.payload.ApiErrorResponse;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import com.htet.employeemanagementapi.util.api.payload.MessageRes;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<Map<String, String>> validationError(DtoValidationException e) {
        return CustomApiResponse.validationError(e.getMessages());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<MessageRes> httpConvertError(HttpMessageNotReadableException e) {
        return CustomApiResponse.badRequest(new MessageRes(e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<MessageRes> missingParamError(MissingServletRequestParameterException e) {
        return CustomApiResponse.badRequest(new MessageRes(e.getMessage()));
    }

    @ExceptionHandler
    public CustomApiResponse<MessageRes> usernameNotFoundError(AuthenticationException e) {
        if (e instanceof UsernameNotFoundException)
            return CustomApiResponse.authenticationError(new MessageRes("Login Fail! Something was wrong!"));
        if (e instanceof LockedException)
            return CustomApiResponse.authenticationError(new MessageRes("Please contact to admin for you account! Your Account is locked by admin."));
        if (e instanceof DisabledException)
            return CustomApiResponse.authenticationError(new MessageRes("Please contact to admin for you account! Your Account is inactive by admin."));
        return CustomApiResponse.authenticationError(new MessageRes(e.getMessage()));
    }

    // for access denied exception
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CustomApiResponse<MessageRes> accessDeniedError() {
        return CustomApiResponse.accessDeniedError(new MessageRes("You Don't have access to reach this page! If you have any query, kindly ask admin."));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<MessageRes> expiredJwtError(JwtException e) {
        if (e instanceof ExpiredJwtException)
            return CustomApiResponse.jwtExpiredError(new MessageRes("Your Jwt token is Expired!"));
        if (e instanceof SignatureException)
            return CustomApiResponse.wrongJwtSignature(new MessageRes("Wrong Jwt token!"));
        return CustomApiResponse.jwtError(new MessageRes(e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<MessageRes> expiredJwtError(WrongRefreshTokenException e) {
        return CustomApiResponse.wrongRefreshTokenError(new MessageRes(e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<MessageRes> expiredJwtError(ExpiredRefreshTokenException e) {
        return CustomApiResponse.refreshTokenExpiredError(new MessageRes(e.getMessage()));
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomApiResponse<MessageRes> badRequest(BadRequestException e) {
        return CustomApiResponse.badRequest(new MessageRes(e.getMessage()));
    }
}
