package com.htet.employeemanagementapi.util.api.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchReq extends GetList{

    private String employeeName;
    private String departmentName;
}
