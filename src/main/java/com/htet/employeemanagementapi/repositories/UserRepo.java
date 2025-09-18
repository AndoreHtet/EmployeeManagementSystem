package com.htet.employeemanagementapi.repositories;

import com.htet.employeemanagementapi.entities.User;
import com.htet.employeemanagementapi.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepo extends BaseRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);


}
