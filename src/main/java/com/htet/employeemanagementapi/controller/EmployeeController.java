package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;
import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import com.htet.employeemanagementapi.services.EmployeeService;
import com.htet.employeemanagementapi.util.DtoUtil;
import com.htet.employeemanagementapi.util.api.payload.ApiResponse;
import com.htet.employeemanagementapi.util.api.payload.EmployeeSearchReq;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DtoUtil dtoUtil;

    @PostMapping("/employee-list")
    public ApiResponse<DataTableResDTO<EmployeeDTO>> getEmployeeList(@RequestBody @Validated EmployeeSearchReq reqDto,
                                                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new DtoValidationException(bindingResult);
        }
        var dataTableSearch = dtoUtil.transformReqToSearchDto(reqDto);
        var employeeList = employeeService.searchEmployee(dataTableSearch);
        return ApiResponse.success(employeeList);
    }
}
