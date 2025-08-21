package com.htet.employeemanagementapi.repositories;

import com.htet.employeemanagementapi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmployeeId(String employeeId);

    Optional<Employee> findTopByOrderByEmployeeIdDesc();
}
