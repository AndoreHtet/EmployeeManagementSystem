package com.htet.employeemanagementapi.dto.employee;

import com.htet.employeemanagementapi.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {

    private String employeeId;
    private String name;
    private String email;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String departmentName;



    public EmployeeDetailDTO(Employee employee){
        this.employeeId = employee.getEmployeeId() != null ? employee.getEmployeeId() : "";
        this.name = employee.getName() != null ? employee.getName() : "";
        this.email = employee.getEmail() != null ? employee.getEmail() : "";
        this.gender = employee.getGender() != null ? employee.getGender().toString() : "";
        this.birthDate = employee.getBirthDate() != null ? employee.getBirthDate().toString() : "";
        this.phoneNumber = employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "";
        this.departmentName = employee.getDepartment().getName();
    }
}
