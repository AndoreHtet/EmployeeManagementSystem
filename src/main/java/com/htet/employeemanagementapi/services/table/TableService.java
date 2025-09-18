package com.htet.employeemanagementapi.services.table;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;


public interface TableService {

    void sort(CriteriaBuilder cb, CriteriaQuery<?> cq, Root<?> root,
              String sortColumnName, String sortDirection);
}
