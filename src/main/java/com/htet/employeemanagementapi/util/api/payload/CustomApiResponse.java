package com.htet.employeemanagementapi.util.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomApiResponse<T>{

    private Status status;
    private LocalDateTime issuedAt;
    private T payload;

    public static <T> CustomApiResponse<T> success(T data){
        return new CustomApiResponse<>(Status.SUCCESS, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> badRequest(T data){
        return new CustomApiResponse<>(Status.BAD_REQUEST, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> authenticationError(T data) {
        return new CustomApiResponse<>(Status.AUTHENTICATION_FAILURE, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> accessDeniedError(T data) {
        return new CustomApiResponse<>(Status.ACCESS_DENIED, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> jwtExpiredError(T data) {
        return new CustomApiResponse<>(Status.JWT_EXPIRED, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> wrongJwtSignature(T data) {
        return new CustomApiResponse<>(Status.JWT_WRONG_SIGNATURE, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> jwtError(T data) {
        return new CustomApiResponse<>(Status.JWT_ERROR, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> wrongRefreshTokenError(T data) {
        return new CustomApiResponse<>(Status.WRONG_REFRESH_TOKEN, LocalDateTime.now(), data);
    }

    public static <T> CustomApiResponse<T> refreshTokenExpiredError(T data) {
        return new CustomApiResponse<>(Status.REFRESH_TOKEN_EXPIRED, LocalDateTime.now(), data);
    }



    public enum Status{
        SUCCESS,
        BAD_REQUEST,
        AUTHENTICATION_FAILURE,
        ACCESS_DENIED,
        WRONG_REFRESH_TOKEN,
        REFRESH_TOKEN_EXPIRED,
        JWT_EXPIRED,
        JWT_WRONG_SIGNATURE,
        JWT_ERROR
    }
}
