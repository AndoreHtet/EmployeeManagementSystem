package com.htet.employeemanagementapi.services.employee;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeDetailDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeFormDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeSearchDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;
import org.apache.coyote.BadRequestException;

public interface EmployeeService {

    DataTableResDTO<EmployeeDTO> searchEmployee(EmployeeSearchDTO employeeSearchDTO);

    String getLoginUserEmail();

    void manageEmployee(EmployeeFormDTO employeeDTO) throws BadRequestException;

    boolean isEmployeeExist(Long id);

    String generateEmployeeId();

    EmployeeDetailDTO getEmployeeDetailById(Long id) throws BadRequestException;

    void deleteEmployee(Long id) throws BadRequestException;


}
