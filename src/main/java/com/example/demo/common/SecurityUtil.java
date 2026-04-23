package com.example.demo.common;

import com.example.demo.security.AuthUser;
import com.example.demo.user.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class SecurityUtil {
    public static UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
    public static UUID getCurrentUserId() {
        return ((AuthUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}
