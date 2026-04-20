package com.example.demo.user;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .data(userService.createUser(request))
                .build();
    }
}
