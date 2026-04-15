package com.example.demo.task.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TaskResponse {
    private UUID id;
    private String title;
    private String status;
    private String assignedToName;
}
