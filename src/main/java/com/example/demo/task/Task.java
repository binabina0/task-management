package com.example.demo.task;

import com.example.demo.common.BaseEntity;
import com.example.demo.group.Group;
import com.example.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private String priority;
    private LocalDateTime deadline;
    @ManyToOne
    private Group group;
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private UserEntity assignedTo;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;

}
