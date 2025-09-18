package com.htet.employeemanagementapi.dto.user;

import com.htet.employeemanagementapi.dto.searchData.EssentialDataForTable;
import com.htet.employeemanagementapi.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Getter
public class UserSearchDTO extends EssentialDataForTable {

    private String name;

    public Predicate[] predicates(CriteriaBuilder cb , Root<User> root) {
        var list = new ArrayList<Predicate>();
        if (StringUtils.hasText(name)) {
            list.add(cb.like(cb.lower(root.get("user.name")),
                    name.toLowerCase().concat("%")));
        }
        return list.toArray(Predicate[]::new);
    }
}
