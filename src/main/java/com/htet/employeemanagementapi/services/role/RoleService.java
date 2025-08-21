package com.htet.employeemanagementapi.services.role;

import org.springframework.security.core.Authentication;

public interface RoleService {

    String getCurrentRoleName(Authentication auth);
}
