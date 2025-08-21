package com.htet.employeemanagementapi.dto.user;

import com.htet.employeemanagementapi.entities.Role;
import com.htet.employeemanagementapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String roleName;


    public UserDTO(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roleName = user.getRole().getName();
    }

}
