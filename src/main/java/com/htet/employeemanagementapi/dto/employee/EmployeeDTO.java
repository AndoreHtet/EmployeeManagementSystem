package com.htet.employeemanagementapi.dto.employee;

import com.htet.employeemanagementapi.entities.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDTO {

    private Long id;
    private String employeeId;
    private String name;
    private String email;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String departmentName;
    private Long departmentId;

    private int rowNo;


    public EmployeeDTO(Employee entity) {
        this.employeeId = entity.getEmployeeId() != null ? entity.getEmployeeId() : "";
        this.id = entity.getId();
        this.name = entity.getName() != null ? entity.getName() : "";
        this.email = entity.getEmail() != null ? entity.getEmail() : "";
        this.gender = entity.getGender() != null ? entity.getGender().toString() : "";
        this.birthDate = entity.getBirthDate() != null ? entity.getBirthDate().toString() : "";
        this.phoneNumber = entity.getPhoneNumber() != null ? entity.getPhoneNumber() : "";
        this.departmentName = entity.getDepartment().getName();
        this.departmentId = entity.getDepartment().getId();
    }
}
