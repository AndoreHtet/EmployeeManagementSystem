package com.htet.employeemanagementapi.dto.employee;

import com.htet.employeemanagementapi.entities.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String departmentName;

    private int rowNo;


    public EmployeeDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.gender = entity.getGender().toString();
        this.birthDate = entity.getBirthDate().toString();
        this.phoneNumber = entity.getPhoneNumber();
        this.departmentName = entity.getDepartment().getName();
    }
}
