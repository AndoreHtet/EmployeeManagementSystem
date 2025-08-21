package com.htet.employeemanagementapi.dto.security;

import jakarta.validation.constraints.NotBlank;

public record LoginReq(
        @NotBlank(message = "Email is require!")
        String email,
        @NotBlank(message = "Password is require!")
        String password
) {
}

