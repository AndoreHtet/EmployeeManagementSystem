package com.htet.employeemanagementapi.services.employee;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeSearchDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;

public interface EmployeeService {

    DataTableResDTO<EmployeeDTO> searchEmployee(EmployeeSearchDTO employeeSearchDTO);

    String getLoginUserEmail();


}
