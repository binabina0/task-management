package com.example.demo.security;

import com.example.demo.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails  implements UserDetails {
    private final UserEntity user;
    public Collection getAuthorities() {
        return List.of();
    }
    public String getPassword() {
        return user.getPassword();
    }
    public String getUsername() {
        return user.getEmail();
    }
}
