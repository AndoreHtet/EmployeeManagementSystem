package com.htet.employeemanagementapi.repositories;

import com.htet.employeemanagementapi.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
