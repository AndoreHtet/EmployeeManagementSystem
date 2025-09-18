package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeDetailDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeFormDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;
import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import com.htet.employeemanagementapi.services.employee.EmployeeService;
import com.htet.employeemanagementapi.util.DtoUtil;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import com.htet.employeemanagementapi.util.api.payload.DetailMessageRes;
import com.htet.employeemanagementapi.util.api.payload.EmployeeSearchReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.AccessDeniedException;
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


    @PostMapping("/employee-list")
    public CustomApiResponse<DataTableResDTO<EmployeeDTO>> getEmployeeList(@RequestBody @Validated EmployeeSearchReq reqDto,
                                                                           BindingResult bindingResult) {
        log.info("Request payload for employee list: {}", reqDto);
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        var dataTableSearch = dtoUtil.transformReqToSearchDto(reqDto);
        var employeeList = employeeService.searchEmployee(dataTableSearch);
        log.info("Employee List Retrieved Successfully: {}", employeeList);
        return CustomApiResponse.success(employeeList);
    }

    @PostMapping("/create-employee")
    public CustomApiResponse<DetailMessageRes> createEmployee(@RequestBody @Validated EmployeeFormDTO employeeDTO, BindingResult bindingResult,
                                                              boolean isEdit) throws BadRequestException {
        log.info("Request payload for creating employee: {}", employeeDTO);

        if (!isEdit && employeeDTO.getId() != null)
            throw new AccessDeniedException("Can't create on edit path!");

        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        employeeService.manageEmployee(employeeDTO);

        return CustomApiResponse.success(new DetailMessageRes(employeeDTO.getId() != null ? "Successfully Edited" : "Successfully Created"));
    }

    @PostMapping("/edit-employee")
    public CustomApiResponse<DetailMessageRes> editEmployee(@RequestBody @Validated EmployeeFormDTO employeeFormDTO, BindingResult result)
            throws BadRequestException {
        log.info("Request payload for editing employee: {}", employeeFormDTO);
        if (employeeFormDTO.getId() == null || employeeFormDTO.getId() <= 0)
            throw new AccessDeniedException("Can't create on edit path!");

        if (!employeeService.isEmployeeExist(employeeFormDTO.getId()))
            throw new BadRequestException("Employee id doesn't exist!");

        return createEmployee(employeeFormDTO, result, true);
    }

    @GetMapping("/employee-detail")
    public CustomApiResponse<EmployeeDetailDTO> employeeDetail(@RequestParam Long id) throws BadRequestException {
        log.info("Request payload for employee detail: {}", id);
        return CustomApiResponse.success(employeeService.getEmployeeDetailById(id));
    }

    @DeleteMapping("/delete-employee")
    public CustomApiResponse<String> deleteEmployee(@RequestParam Long id) throws BadRequestException {
        if (!employeeService.isEmployeeExist(id))
            throw new BadRequestException("Employee id doesn't exist!");
        employeeService.deleteEmployee(id);
        return CustomApiResponse.success("Successfully Deleted Employee Record.");
    }
}

