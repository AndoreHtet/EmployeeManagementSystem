package com.htet.employeemanagementapi.services.employee;

import com.htet.employeemanagementapi.dto.employee.EmployeeDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeDetailDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeFormDTO;
import com.htet.employeemanagementapi.dto.employee.EmployeeSearchDTO;
import com.htet.employeemanagementapi.dto.searchData.DataTableResDTO;
import com.htet.employeemanagementapi.entities.Department;
import com.htet.employeemanagementapi.entities.Employee;
import com.htet.employeemanagementapi.exceptions.LoginUserNotFoundException;
import com.htet.employeemanagementapi.repositories.DepartmentRepo;
import com.htet.employeemanagementapi.repositories.EmployeeRepo;
import com.htet.employeemanagementapi.services.DefaultDataTableService;
import com.htet.employeemanagementapi.util.CommonUtil;
import com.htet.employeemanagementapi.util.constant.Gender;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    private final EmployeeRepo employeeRepo;

    private final DepartmentRepo departmentRepo;

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

    @Override
    public String getLoginUserEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth instanceof AnonymousAuthenticationToken){
            throw new LoginUserNotFoundException("You need to login first!");
        }
        return auth.getName();
    }

    @Transactional
    @Override
    public void manageEmployee(EmployeeFormDTO employeeDTO) throws BadRequestException {
        boolean isEdit = false;
        Employee employee = new Employee();
        if (CommonUtil.validLong(employeeDTO.getId())){
            isEdit = true;
            employee = employeeRepo.findById(employeeDTO.getId())
                    .orElseThrow(() -> new BadRequestException("Employee doesn't exist"));
            employee.setUpdatedAt(LocalDateTime.now());
        }else{
            employee.setEmployeeId(generateEmployeeId());
            employee.setGender(Gender.valueOf(employeeDTO.getGender()));
            employee.setCreatedAt(LocalDateTime.now());
            employee.setUpdatedAt(LocalDateTime.now());
        }
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        departmentRepo.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new BadRequestException("Department doesn't exist!"));
        employee.setDepartment(new Department(employeeDTO.getDepartmentId()));


        employeeRepo.save(employee);
        if (isEdit == true){
            log.info("Employee updated successfully!");
        }else {
            log.info("Saved Employee: {} ", employee);
        }
    }

    @Override
    public boolean isEmployeeExist(Long id) {
        var employee = employeeRepo.findById(id);
        return employee.isPresent();
    }

    @Override
    public String generateEmployeeId() {
        String prefix = "EMP-";

        var lastEmployee = employeeRepo.findTopByOrderByEmployeeIdDesc();
        if (lastEmployee.isEmpty()){
            return prefix + "0001";
        }

        Employee lastEmployeeObj = lastEmployee.get();
        String lastEmployeeId = lastEmployeeObj.getEmployeeId();

        int number = 0;
        if (lastEmployeeId != null && lastEmployeeId.startsWith(prefix)){
            try{
                number = Integer.parseInt(lastEmployeeId.substring(prefix.length()));
            }catch (NumberFormatException e){
                log.error("Error while parsing employee id: {}", lastEmployeeId);
            }
        }
        number++;
        return String.format("%s%04d", prefix, number);
    }

    @Override
    public EmployeeDetailDTO getEmployeeDetailById(Long id) throws BadRequestException {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new BadRequestException("Employee id doesn't exist"));
        log.info("Employee detail with employeeId {} is retrieved successfully!", employee.getEmployeeId());
        return new EmployeeDetailDTO(employee);
    }

    @Override
    public void deleteEmployee(Long id) throws BadRequestException {
        Employee employee = employeeRepo.findById(id).orElseThrow();
        if (!isEmployeeExist(id)){
            throw new BadRequestException("Employee doesn't exist!");
        }else{
            employeeRepo.deleteById(id);
            log.info("Employee with employeeId {} is deleted successfully!", employee.getEmployeeId());
        }

    }


    private Specification<Employee> getSpecification(EmployeeSearchDTO employeeSearchDTO) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (CommonUtil.validString(employeeSearchDTO.getEmployeeName())) {
                predicates.add(cb.like(cb.lower(root.get("name")),
                        employeeSearchDTO.getEmployeeName().toLowerCase() + "%"));
            }

            if (CommonUtil.validString(employeeSearchDTO.getDepartmentName())) {
                predicates.add(cb.like(cb.lower(root.join("department").get("name")),
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
