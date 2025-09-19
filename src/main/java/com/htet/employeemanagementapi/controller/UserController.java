package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.user.UserDetail;
import com.htet.employeemanagementapi.dto.user.UserRedeemPasswordDTO;
import com.htet.employeemanagementapi.dto.user.UserResetPasswordDTO;
import com.htet.employeemanagementapi.dto.user.UserSearchDTO;
import com.htet.employeemanagementapi.services.user.UserService;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import com.htet.employeemanagementapi.util.api.payload.TableResponse;
import com.htet.employeemanagementapi.util.handler.BindingResultHandler;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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

    @PostMapping("/redeem-password")
    public ResponseEntity<Map<String,String>> redeemPassword(@RequestBody @Valid UserRedeemPasswordDTO userRedeemPasswordDTO, BindingResult result){
        userService.redeemPassword(userRedeemPasswordDTO.email());
        return ResponseEntity.ok().body(Map.of("message","Send the redeem password"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody @Valid UserResetPasswordDTO userResetPasswordDTO, BindingResult result) throws BadRequestException {
        userService.resetPassword(userResetPasswordDTO.email(),
                userResetPasswordDTO.optCode(),
                userResetPasswordDTO.password());
        return ResponseEntity.ok().body(Map.of("message","Reset password successfully"));
    }

}
