package com.htet.employeemanagementapi.services.department;

import com.htet.employeemanagementapi.dto.department.DepartmentDTO;
import com.htet.employeemanagementapi.dto.department.DepartmentFormDTO;
import com.htet.employeemanagementapi.entities.Department;
import com.htet.employeemanagementapi.repositories.DepartmentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;


    @Override
    @Cacheable("departments")
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departmentList = departmentRepo.findAll();
        log.info("Department list size from db : {}", departmentList.size());
        return departmentList.stream()
                .map(DepartmentDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    @CacheEvict(value = "departments", allEntries = true)
    public void createDepartment(DepartmentFormDTO departmentFormDTO) {
        Department department = new Department();
        department.setCode(departmentFormDTO.getCode());
        department.setName(departmentFormDTO.getName());
        department.setRegion(departmentFormDTO.getRegion());
        department.setCreatedAt(LocalDateTime.now());
        department.setUpdatedAt(LocalDateTime.now());
        log.info("Department created successfully");
        departmentRepo.save(department);
    }


}
