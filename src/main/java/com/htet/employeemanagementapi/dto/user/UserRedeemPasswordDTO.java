package com.htet.employeemanagementapi.dto.user;

import jakarta.validation.constraints.Email;


public record UserRedeemPasswordDTO(
        @Email String email
) {
}
