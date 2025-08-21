package com.htet.employeemanagementapi.services.security;

import com.htet.employeemanagementapi.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.getUserDetailByEmail(email)
                .map(user -> User
                        .withUsername(email)
                        .authorities(user.getRoleName())
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .build()
                ).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
