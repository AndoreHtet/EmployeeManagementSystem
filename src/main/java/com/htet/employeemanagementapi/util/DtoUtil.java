package com.htet.employeemanagementapi.util;

import com.htet.employeemanagementapi.dto.employee.EmployeeSearchDTO;
import com.htet.employeemanagementapi.util.api.payload.EmployeeSearchReq;
import org.springframework.stereotype.Component;

@Component
public class DtoUtil {

    public EmployeeSearchDTO transformReqToSearchDto(EmployeeSearchReq req){
        return EmployeeSearchDTO.builder()
                .employeeName(req.getEmployeeName())
                .departmentName(req.getDepartmentName())
                .pageNo(req.getPageNo())
                .length(req.getLength())
                .dir(req.getDir())
                .columnName(req.getColumnName())
                .build();
    }
}
