package com.htet.employeemanagementapi.services.user;

import com.htet.employeemanagementapi.dto.user.UserDTO;
import com.htet.employeemanagementapi.entities.User;
import com.htet.employeemanagementapi.repositories.UserRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    @Override
    public Optional<UserDTO> getUserDetailByEmail(String email) {
        Function<CriteriaBuilder, CriteriaQuery<UserDTO>> searchByEmail = cb -> {
            var cq = cb.createQuery(UserDTO.class);
            var root = cq.from(User.class);
            cq.select(cb.construct(
                    UserDTO.class,
                    root.get("email"),
                    root.get("password"),
                    root.get("role").get("name")
            ));
            cq.where(cb.equal(root.get("email"), email));
            return cq;
        };
        return userRepo.findOne(searchByEmail);
    }
}
