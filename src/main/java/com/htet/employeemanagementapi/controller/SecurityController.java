package com.htet.employeemanagementapi.controller;

import com.htet.employeemanagementapi.dto.security.LoginReq;
import com.htet.employeemanagementapi.dto.security.RefreshTokenReq;
import com.htet.employeemanagementapi.exceptions.DtoValidationException;
import com.htet.employeemanagementapi.exceptions.ExpiredRefreshTokenExceptions;
import com.htet.employeemanagementapi.exceptions.WrongRefreshTokenExceptions;
import com.htet.employeemanagementapi.security.JwtTokenProvider;
import com.htet.employeemanagementapi.services.user.UserService;
import com.htet.employeemanagementapi.dto.security.LoginResult;
import com.htet.employeemanagementapi.util.api.payload.CustomApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public CustomApiResponse<LoginResult> signIn(@RequestBody @Valid LoginReq loginReq, BindingResult result){
        if (result.hasErrors()){
            throw new DtoValidationException(result);
        }

        var authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginReq.email(), loginReq.password()));
        var accessToken = tokenProvider.generateToken(authentication,false);
        var refreshToken = tokenProvider.generateToken(authentication, true);
        log.info("Login Successful: {} ", loginReq.email());
        return CustomApiResponse.success(new LoginResult(accessToken,refreshToken));
    }

    @PostMapping("refreshToken")
    public CustomApiResponse<LoginResult> refreshToken(@RequestBody @Valid RefreshTokenReq refreshTokenReq, BindingResult result){
        if (result.hasErrors()){
            throw new DtoValidationException(result);
        }

        try {
            Authentication auth = jwtTokenProvider.parseToken(refreshTokenReq.refreshToken());
            var email = auth.getName();
            userService.getUserDetailByEmail(email).orElseThrow(() -> new WrongRefreshTokenExceptions("Wrong Refresh Token!"));

            if (auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            var accessToken =  jwtTokenProvider.generateToken(auth,false);
            var refreshToken = jwtTokenProvider.generateToken(auth,true);
            return CustomApiResponse.success(new LoginResult(accessToken,refreshToken));
        }catch (ExpiredJwtException e){
            throw new ExpiredRefreshTokenExceptions("Token Expired! Please login again");
        }
    }


}
