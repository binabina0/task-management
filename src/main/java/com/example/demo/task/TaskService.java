package com.example.demo.task;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.dto.TaskResponse;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest request) {
        UserEntity user = userRepository.findById(request.getAssignedToId()).orElseThrow();
        UserEntity creator = userRepository.findById(request.getCreatedById()).orElseThrow();
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow();
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.TODO)
                .group(group)
                .assignedTo(user)
                .createdBy(creator)
                .build();
        Task saved =  taskRepository.save(task);
        return TaskResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .status(saved.getStatus().name())
                .assignedToName(user.getName())
                .build();

    }

    public List<TaskResponse> getTaskByUser(UUID userId) {
        return taskRepository.findByAssignedToId(userId)
                .stream()
                .map(task -> TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .status(task.getStatus().name())
                        .assignedToName(task.getAssignedTo().getName())
                        .build())
                .toList();
    }

    public void updateStatus(UUID taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(status);
        taskRepository.save(task);
    }
}
