package com.htet.employeemanagementapi.dto.roleAccess;

import com.htet.employeemanagementapi.entities.Role;
import com.htet.employeemanagementapi.entities.RoleAccess;
import com.htet.employeemanagementapi.util.constant.CrudOperation;
import com.htet.employeemanagementapi.util.constant.RequestMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAccessDTO {

    private Long id;
    private String name;
    private String url;
    private RequestMethod requestMethod;
    private CrudOperation crudOperation;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updatedAt;



    public RoleAccessDTO(RoleAccess entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.url = entity.getUrl();
        this.requestMethod = entity.getRequestMethod();
        this.crudOperation = entity.getCrudOperation();
        this.description = entity.getDescription();
        this.created = entity.getCreated();
        this.updatedAt = entity.getUpdatedAt();
    }

}
