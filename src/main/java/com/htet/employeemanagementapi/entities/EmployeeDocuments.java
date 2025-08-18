package com.htet.employeemanagementapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "employee_document")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDocuments extends BaseField implements Serializable {

    private String documentName;

    private String position;

    private String address;

    private BigDecimal salary;

    private String duration;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;



}
