package com.example.demo.membership;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;

    public Membership addMember(UUID userId, UUID groupId, Role role) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow();
        Group group = groupRepository.findById(groupId)
                .orElseThrow();
        Membership membership = Membership.builder()
                . user(user)
                .group(group)
                .role(role)
                .build();
        return membershipRepository.save(membership);
    }

}
