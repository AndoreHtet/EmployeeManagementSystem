package com.htet.employeemanagementapi.dto.department;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFormDTO {

    @NotBlank(message = "Department code is required")
    private String code;
    @NotBlank(message = "Department name is required")
    private String name;
    @NotBlank(message = "Department region is required")
    private String region;
}
