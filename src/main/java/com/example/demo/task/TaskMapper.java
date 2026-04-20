package com.example.demo.task;

import com.example.demo.task.dto.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .status(task.getStatus().name())
                .assignedToName(task.getAssignedTo().getName())
                .build();
    }
}
