package com.htet.employeemanagementapi.dto.department;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;
    private String code;
    private String name;
    private String region;
}
