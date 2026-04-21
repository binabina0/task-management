package com.example.demo.membership;

import com.example.demo.group.Group;
import com.example.demo.membership.dto.MembershipResponse;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public MembershipResponse toResponse(Membership membership) {
        return MembershipResponse.builder()
                .id(membership.getId())
                .username(membership.getUser().getName())
                .groupName(membership.getGroup().getName())
                .role(membership.getRole())
                .build();
    }
}
