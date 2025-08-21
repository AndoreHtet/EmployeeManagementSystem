package com.htet.employeemanagementapi.services.department;

import com.htet.employeemanagementapi.dto.department.DepartmentDTO;
import com.htet.employeemanagementapi.dto.department.DepartmentFormDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> getAllDepartments();

    void createDepartment(DepartmentFormDTO departmentFormDTO);
}
