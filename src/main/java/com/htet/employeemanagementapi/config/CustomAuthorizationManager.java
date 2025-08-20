package com.htet.employeemanagementapi.config;

import com.htet.employeemanagementapi.services.role.RoleService;
import com.htet.employeemanagementapi.services.roleAccess.RoleAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final RoleService roleService;

    private final RoleAccessService roleAccessService;



    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        var auth = authentication.get();
        var httpRequest = object.getRequest();
        String requestUrl = httpRequest.getRequestURI();
        String requestMethod = httpRequest.getMethod();

        if (auth instanceof AnonymousAuthenticationToken){
            return new AuthorizationDecision(false);
        }
        if (auth.isAuthenticated()) {
            String currentRoleName = roleService.getCurrentRoleName(auth);

            var hasRoleAccess = roleAccessService.findRoleAccessByRoleName(currentRoleName)
                    .stream()
                    .filter(ra -> ra.getUrl().equals(requestUrl) && ra.getRequestMethod().name().equals(requestMethod))
                    .findAny();
            log.info("Auth check -> URL: {}, Method: {}, Role: {}", requestUrl, requestMethod, currentRoleName);

            if (hasRoleAccess.isPresent()) {
                return new AuthorizationDecision(true);
            }
        }
            return new AuthorizationDecision(false);

        }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }
    }

