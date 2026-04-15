package com.example.demo.group.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GroupRequest {
    private String name;
    private String description;
    private UUID userId;
}
