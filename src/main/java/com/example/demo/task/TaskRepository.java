package com.example.demo.task;

import com.example.demo.task.dto.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByAssignedToId(UUID userId);
}
