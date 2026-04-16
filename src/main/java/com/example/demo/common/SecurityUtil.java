package com.example.demo.common;

import com.example.demo.user.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
