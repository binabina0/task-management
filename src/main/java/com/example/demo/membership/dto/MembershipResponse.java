package com.example.demo.membership.dto;

import com.example.demo.membership.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MembershipResponse {
    private UUID id;
    private String username;
    private String groupName;
    private Role role;
}
