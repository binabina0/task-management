package com.example.demo.task.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private UUID groupId;
    private UUID assignedToId;
    private UUID createdById;

}
