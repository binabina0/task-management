package com.example.demo.task;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.dto.TaskResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ApiResponse<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        return ApiResponse.<TaskResponse>builder()
                .success(true)
                .data(taskService.createTask(request))
                .build();
    }
    @GetMapping("/user/{userId}")
    public ApiResponse<List<TaskResponse>> getUserTasks(@PathVariable("userId") UUID userId) {
        return ApiResponse.<List<TaskResponse>>builder()
                .success(true)
                .data(taskService.getTaskByUser(userId))
                .build();
    }
    @PatchMapping("/{taskId}/status")
    public void updateStatus(@PathVariable UUID taskId, @RequestParam TaskStatus status) {
        taskService.updateStatus(taskId, status);
    }
    @GetMapping()
    public ApiResponse<Page<TaskResponse>> getTasks(@RequestParam int page,
                                                    @RequestParam int size) {
        return ApiResponse.<Page<TaskResponse>>builder()
                .success(true)
                .data(taskService.getTasks(page,size))
                .build();
    }
}
