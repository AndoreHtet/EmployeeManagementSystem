package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.user.UserDetail;
import com.htet.employeemanagementapi.dto.user.UserSearchDTO;
import com.htet.employeemanagementapi.services.user.UserService;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import com.htet.employeemanagementapi.util.api.payload.TableResponse;
import com.htet.employeemanagementapi.util.handler.BindingResultHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/userList")
    public CustomApiResponse<TableResponse<UserDetail>> getUserList(
            @RequestBody UserSearchDTO searchDTO, BindingResult result
            ){
        BindingResultHandler.checkBindingResultError(result);
        var data = userService.userList(searchDTO);
        return CustomApiResponse.success(data);
    }

}
