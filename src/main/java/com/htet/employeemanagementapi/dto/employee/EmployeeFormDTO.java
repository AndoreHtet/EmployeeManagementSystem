package com.htet.employeemanagementapi.dto.employee;

import com.htet.employeemanagementapi.entities.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFormDTO {

    private Long id;
    private String employeeId;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Gender is required")
    private String gender;
    private LocalDate birthDate;
    private String phoneNumber;
    @NotNull(message = "Department is required")
    @Positive(message = "Invalid Department Id!")
    private Long departmentId;

    public EmployeeFormDTO(Employee employee){
        this.id = employee.getId();
        this.employeeId = employee.getEmployeeId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.gender = employee.getGender().toString();
        this.birthDate = employee.getBirthDate();
        this.phoneNumber = employee.getPhoneNumber();
        this.departmentId = employee.getDepartment().getId();
    }
}
