package com.htet.employeemanagementapi.dto.searchData;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EssentialDataForTable {

    @NotNull(message = "Page number can't be empty or null!")
    @Positive(message = "Page number be positive number")
    private Integer pageNo;
    private Integer pageSize;
    private String sortColumnName;
    private String sortDirection;
}
