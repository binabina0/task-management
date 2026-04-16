package com.example.demo.membership;

import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;

    public Membership addMember(UUID userId, UUID groupId, Role role) {
        checkAdmin(groupId);
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
    public void checkAdmin(UUID groupId) {
        UUID userId = getCurrentUser().getId();
        Membership membership = membershipRepository
                .findByUserIdAndGroupId(userId, groupId)
                .orElseThrow(() -> new RuntimeException("Not a member of this group"));
        if(membership.getRole() != Role.ADMIN) {
            throw new RuntimeException("Access denied");
        }
    }
    public void checkMember(UUID groupId) {
        UUID userId = getCurrentUser().getId();
        membershipRepository
                .findByUserIdAndGroupId(userId, groupId)
                .orElseThrow(() -> new RuntimeException("Not a member of this group"));
    }
    public void changeRole(UUID targetUser, UUID groupId, Role newRole) {
        checkAdmin(groupId);
        Membership membership = membershipRepository
                .findByUserIdAndGroupId(targetUser,groupId)
                .orElseThrow(() -> new RuntimeException("Not a member of this group"));
        membership.setRole(newRole);
        membershipRepository.save(membership);
    }
}
