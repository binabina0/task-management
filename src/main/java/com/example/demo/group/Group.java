package com.example.demo.group;

import com.example.demo.common.BaseEntity;
import com.example.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
}
