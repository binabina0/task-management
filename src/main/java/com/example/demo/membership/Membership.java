package com.example.demo.membership;

import com.example.demo.common.BaseEntity;
import com.example.demo.group.Group;
import com.example.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memberships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Membership extends BaseEntity {
     @ManyToOne
    private UserEntity user;
    @ManyToOne
    private Group group;
    @Enumerated(EnumType.STRING)
    private Role role;


}
