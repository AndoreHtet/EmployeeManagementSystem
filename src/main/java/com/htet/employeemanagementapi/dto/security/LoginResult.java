package com.htet.employeemanagementapi.dto.security;

public record LoginResult(String accessToken, String refreshToken) {
}
