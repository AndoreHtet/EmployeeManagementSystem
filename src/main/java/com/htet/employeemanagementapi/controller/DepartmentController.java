package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.department.DepartmentDTO;
import com.htet.employeemanagementapi.dto.department.DepartmentFormDTO;
import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import com.htet.employeemanagementapi.services.department.DepartmentService;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import com.htet.employeemanagementapi.util.api.payload.DetailMessageRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping("/get-department-list")
    public CustomApiResponse<List<DepartmentDTO>> getAllDepartmentsList() {
        log.info("Get all departments list");
        return CustomApiResponse.success(departmentService.getAllDepartments());
    }


    @PostMapping("/create-department")
    public CustomApiResponse<String> createDepartment(@RequestBody @Validated DepartmentFormDTO departmentFormDTO,
                                                      BindingResult bindingResult) {
        log.info("Create department req: {}", departmentFormDTO);
        if (bindingResult.hasErrors()) {
            throw new DtoValidationException(bindingResult);
        }
        departmentService.createDepartment(departmentFormDTO);
        return CustomApiResponse.success("Department create successfully!");
    }

}
