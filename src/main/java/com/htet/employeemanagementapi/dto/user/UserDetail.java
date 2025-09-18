package com.htet.employeemanagementapi.dto.user;

import com.htet.employeemanagementapi.entities.User;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record UserDetail(
        String name,
        String email

) {
    public static void select(CriteriaQuery<UserDetail> criteria, Root<User> root) {
        criteria.multiselect(
                root.get("name"),
                root.get("email")
        );
    }
}
