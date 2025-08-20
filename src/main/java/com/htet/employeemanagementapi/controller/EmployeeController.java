package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;
import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import com.htet.employeemanagementapi.services.employee.EmployeeService;
import com.htet.employeemanagementapi.util.DtoUtil;
import com.htet.employeemanagementapi.util.api.payload.ApiErrorResponse;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import com.htet.employeemanagementapi.util.api.payload.EmployeeSearchReq;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DtoUtil dtoUtil;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiErrorResponse.class)}
    )
    @PostMapping("/employee-list")
    public CustomApiResponse<DataTableResDTO<EmployeeDTO>> getEmployeeList(@RequestBody @Validated EmployeeSearchReq reqDto,
                                                                           BindingResult bindingResult){
        log.info("Request payload for employee list: {}",reqDto);
        if (bindingResult.hasErrors()){
            throw new DtoValidationException(bindingResult);
        }
        var dataTableSearch = dtoUtil.transformReqToSearchDto(reqDto);
        var employeeList = employeeService.searchEmployee(dataTableSearch);
        log.info("Employee List Retrieved Successfully: {}" , employeeList);
        return CustomApiResponse.success(employeeList);
    }
}
