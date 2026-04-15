package com.example.demo.task;

import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }
    @GetMapping("/user/{userId}")
    public List<TaskResponse> getUserTasks(@PathVariable("userId") UUID userId) {
        return taskService.getTaskByUser(userId);
    }
}
