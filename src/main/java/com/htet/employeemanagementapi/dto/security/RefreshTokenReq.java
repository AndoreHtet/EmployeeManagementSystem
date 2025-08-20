package com.htet.employeemanagementapi.dto.security;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenReq(
        @NotBlank(message = "Refresh token cannot be empty!")
        String refreshToken
) {
}