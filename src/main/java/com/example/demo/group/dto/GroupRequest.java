package com.example.demo.group.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class GroupRequest {
    @NotBlank(message = "Group name required")
    private String name;
    private String description;
    private UUID userId;
}
