package com.htet.employeemanagementapi.services.roleAccess;

import com.htet.employeemanagementapi.dto.roleAccess.RoleAccessDTO;

import java.util.List;

public interface RoleAccessService {

    List<RoleAccessDTO> findRoleAccessByRoleName(String roleName);
}
