package com.example.demo.membership.dto;

import com.example.demo.membership.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Data
public class MembershipRequest {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID groupId;
    @NotNull
    private Role role;
}
