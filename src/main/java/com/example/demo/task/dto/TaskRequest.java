package com.example.demo.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskRequest {
    @NotBlank(message = "Title required")
    private String title;
    private String description;
    @NotNull(message = "GroupId is required")
    private UUID groupId;
    @NotNull(message = "Assigned user required")
    private UUID assignedToId;
    private UUID createdById;

}
