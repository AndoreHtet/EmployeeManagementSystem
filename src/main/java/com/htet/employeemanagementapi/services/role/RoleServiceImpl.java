package com.htet.employeemanagementapi.services.role;

import com.htet.employeemanagementapi.exceptions.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    @Override
    public String getCurrentRoleName(Authentication auth) {
        return getRoleNameFromAuthentication(auth);
    }

    private String getRoleNameFromAuthentication(Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
                .orElseThrow(RoleNotFoundException::new);
    }
}
