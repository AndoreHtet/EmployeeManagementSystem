package com.htet.employeemanagementapi.util.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetList {
    @NotNull(message = "Page number can not be empty!")
    @PositiveOrZero(message = "Page number must be positive number!")
    private Integer pageNo;

    @NotNull(message = "Length can not be empty!")
    @Positive(message = "Length must be positive number!")
    private Integer length;

    @NotBlank(message = "Sorting direction is needed!")
    private String dir;

    @NotBlank(message = "Sorting Column is needed!")
    private String columnName;
}
