package com.example.demo.task;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;

    public Task createTask(String title,
                           String description,
                           UUID groupId,
                           UUID assignedToId,
                           UUID createdById) {
        UserEntity user = userRepository.findById(assignedToId).orElseThrow();
        UserEntity creator = userRepository.findById(createdById).orElseThrow();
        Group group = groupRepository.findById(groupId).orElseThrow();
        Task task = Task.builder()
                .title(title)
                .description(description)
                .status(TaskStatus.TODO)
                .group(group)
                .assignedTo(user)
                .createdBy(creator)
                .build();
        return taskRepository.save(task);

    }
}
