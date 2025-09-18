package com.htet.employeemanagementapi.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserResetPasswordDTO(
        @NotBlank String email,
        @NotBlank String optCode,
        @NotBlank @Min(4) String password
        ) {
}
