package com.htet.employeemanagementapi.services.roleAccess;

import com.htet.employeemanagementapi.dto.roleAccess.RoleAccessDTO;
import com.htet.employeemanagementapi.entities.RoleAccess;
import com.htet.employeemanagementapi.repositories.RoleAccessRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleAccessServiceImpl implements RoleAccessService {

    private final RoleAccessRepo roleAccessRepo;

    @Override
    public List<RoleAccessDTO> findRoleAccessByRoleName(String roleName) {
        Function<CriteriaBuilder, CriteriaQuery<RoleAccess>> searchQuery = cb -> {
            CriteriaQuery<RoleAccess> cq = cb.createQuery(RoleAccess.class);
            Root<RoleAccess> root = cq.from(RoleAccess.class);
            var roles = root.join("roles");
            cq.select(root)
                    .where(cb.equal(roles.get("name"), roleName));
            return cq;
        };
        List<RoleAccess> roleAccess = roleAccessRepo.findAll(searchQuery);
        return roleAccess.stream()
                .map(RoleAccessDTO::new)
                .toList();
    }
}
