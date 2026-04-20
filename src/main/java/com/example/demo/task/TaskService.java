package com.example.demo.task;

import com.example.demo.common.exception.NotFoundException;
import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.membership.MembershipService;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.dto.TaskResponse;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final MembershipService membershipService;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        membershipService.checkMember(request.getGroupId());
        UserEntity user = userRepository.findById(request.getAssignedToId()).orElseThrow(() -> new NotFoundException("User not found"));
        UserEntity currentUser = getCurrentUser();
        Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new NotFoundException("Group not found"));
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.TODO)
                .group(group)
                .assignedTo(user)
                .createdBy(currentUser)
                .build();
        Task saved =  taskRepository.save(task);
        return taskMapper.toResponse(saved);

    }

    public List<TaskResponse> getTaskByUser(UUID userId) {
        return taskRepository.findByAssignedToId(userId)
                .stream()
                .map(taskMapper :: toResponse)
                .toList();
    }

    @Transactional
    public void updateStatus(UUID taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public Page<TaskResponse> getTasks(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable)
                .map(taskMapper::toResponse);
    }
}
