package com.htet.employeemanagementapi.dto.employee;

import com.htet.employeemanagementapi.dto.department.DepartmentDTO;
import com.htet.employeemanagementapi.entities.Department;
import com.htet.employeemanagementapi.entities.Employee;
import com.htet.employeemanagementapi.util.constant.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDTO {

    private Long id;
    private Long num;
    private String name;
    private String email;
    private String gender;
    private String birthDate;
    private String phoneNumber;

    private Department department;
    private String departmentName;

    private int rowNo;


    public EmployeeDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.gender = entity.getGender().toString();
        this.birthDate = entity.getBirthDate().toString();
        this.phoneNumber = entity.getPhoneNumber();
        this.department = entity.getDepartment();
        this.departmentName = entity.getDepartment().getName();
    }
}
