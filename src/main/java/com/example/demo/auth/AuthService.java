package com.example.demo.auth;

import com.example.demo.auth.dto.AuthResponse;
import com.example.demo.common.exception.BadRequestException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.security.JwtService;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import com.example.demo.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }
        String token = jwtService.generateToken(user.getId());
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(UserRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user.getId());
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
