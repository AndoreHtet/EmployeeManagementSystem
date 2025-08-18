package com.htet.employeemanagementapi.services;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeSearchDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;
import com.htet.employeemanagementapi.entities.Employee;
import com.htet.employeemanagementapi.repositories.EmployeeRepo;
import com.htet.employeemanagementapi.util.CommonUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepo employeeRepo;

    private final DefaultDataTableService dataTableService;


    @Override
    public DataTableResDTO<EmployeeDTO> searchEmployee(EmployeeSearchDTO employeeSearchDTO) {
        String columnName = convertToColumnName(employeeSearchDTO.getColumn(), employeeSearchDTO.getColumnName());
        employeeSearchDTO.setColumnName(columnName);

        var pageable = dataTableService.getPageable(employeeSearchDTO);
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending());
        }
        var spec = getSpecification(employeeSearchDTO);

        var employeeList = employeeRepo.findAll(spec, pageable);
        var employeeDtoList = convertIntoDto(employeeSearchDTO,employeeList.getContent());
        employeeSearchDTO.setRecordsTotal(employeeRepo.count());
        employeeSearchDTO.setRecordsFiltered(employeeList.getTotalElements());

        return new DataTableResDTO<>(employeeDtoList, employeeSearchDTO);
    }




    private Specification<Employee> getSpecification(EmployeeSearchDTO employeeSearchDTO) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // filter by employee name
            if (CommonUtil.validString(employeeSearchDTO.getEmployeeName())) {
                predicates.add(cb.like(cb.lower(root.get("name")),
                        employeeSearchDTO.getEmployeeName().toLowerCase() + "%"));
            }

            // filter by department name
            if (CommonUtil.validString(employeeSearchDTO.getDepartmentName())) {
                predicates.add(cb.like(cb.lower(root.join("department").get("departmentName")),
                        "%" + employeeSearchDTO.getDepartmentName().toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private String convertToColumnName(Integer columnIndex, String columnName){
        return columnName == null || columnName.isBlank()
                ? convertOrderColumnIndexToColumnName(columnIndex)
                : columnName;
    }

    private List<EmployeeDTO> convertIntoDto(EmployeeSearchDTO searchDTO, List<Employee> employeeList){
        AtomicInteger rowNo = new AtomicInteger(searchDTO.getStart() + 1);
        return employeeList.stream()
                .map(EmployeeDTO::new)
                .peek(at -> {
                    at.setRowNo(rowNo.get());
                    rowNo.getAndIncrement();
                }).toList();
    }

    private String convertOrderColumnIndexToColumnName(int index){
        return switch (index) {
            case 2 -> "name";
            case 3 -> "department.name";
            default -> "created_at";
        };
    }




}
