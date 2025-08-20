package com.htet.employeemanagementapi.repositories;

import com.htet.employeemanagementapi.entities.User;
import com.htet.employeemanagementapi.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends BaseRepository<User, Long>, JpaSpecificationExecutor<User> {

}
