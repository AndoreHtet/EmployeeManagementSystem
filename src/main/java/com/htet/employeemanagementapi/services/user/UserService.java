package com.htet.employeemanagementapi.services.user;

import com.htet.employeemanagementapi.dto.user.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUserDetailByEmail(String email);


}
