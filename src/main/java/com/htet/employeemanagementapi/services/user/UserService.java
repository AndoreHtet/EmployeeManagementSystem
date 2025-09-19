package com.htet.employeemanagementapi.services.user;

import com.htet.employeemanagementapi.dto.user.UserDTO;
import com.htet.employeemanagementapi.dto.user.UserDetail;
import com.htet.employeemanagementapi.dto.user.UserSearchDTO;
import com.htet.employeemanagementapi.util.api.payload.TableResponse;
import org.apache.coyote.BadRequestException;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> getUserDetailByEmail(String email);

    TableResponse<UserDetail> userList(UserSearchDTO searchDTO);

    void resetPassword(String email,String optCode, String password) throws BadRequestException;

    void redeemPassword(String email);

}
