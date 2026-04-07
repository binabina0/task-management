package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity createUser(String email, String password, String name) {
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        return userRepository.save(user);

    }

    public UserEntity getBYId(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
