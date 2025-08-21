package com.htet.employeemanagementapi.entities;

import com.htet.employeemanagementapi.util.constant.CrudOperation;
import com.htet.employeemanagementapi.util.constant.RequestMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_access")
public class RoleAccess extends BaseField implements java.io.Serializable{

    @Serial
    private static final long serialVersionUID = -3236067342532617314L;

    @Column(length = 45,nullable = false,name = "name")
    private String name;

    @Column(nullable = false, name = "url")
    private String url;

    @Column(nullable = false, name = "request_method")
    @Enumerated(EnumType.STRING)
    private RequestMethod requestMethod;

    @Column(nullable = false , name = "crud_operation")
    @Enumerated(EnumType.STRING)
    private CrudOperation crudOperation;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roleAccesses")
    private List<Role> roles;


}

