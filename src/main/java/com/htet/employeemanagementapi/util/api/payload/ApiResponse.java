package com.htet.employeemanagementapi.util.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{

    private Status status;
    private LocalDateTime issuedAt;
    private T payload;

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(Status.SUCCESS, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> badRequest(T data){
        return new ApiResponse<>(Status.BAD_REQUEST, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> authenticationError(T data) {
        return new ApiResponse<>(Status.AUTHENTICATION_FAILURE, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> accessDeniedError(T data) {
        return new ApiResponse<>(Status.ACCESS_DENIED, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> jwtExpiredError(T data) {
        return new ApiResponse<>(Status.JWT_EXPIRED, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> wrongJwtSignature(T data) {
        return new ApiResponse<>(Status.JWT_WRONG_SIGNATURE, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> jwtError(T data) {
        return new ApiResponse<>(Status.JWT_ERROR, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> wrongRefreshTokenError(T data) {
        return new ApiResponse<>(Status.WRONG_REFRESH_TOKEN, LocalDateTime.now(), data);
    }

    public static <T> ApiResponse<T> refreshTokenExpiredError(T data) {
        return new ApiResponse<>(Status.REFRESH_TOKEN_EXPIRED, LocalDateTime.now(), data);
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
