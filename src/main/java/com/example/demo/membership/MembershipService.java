package com.example.demo.membership;

import com.example.demo.common.exception.ForbiddenException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.membership.dto.MembershipRequest;
import com.example.demo.membership.dto.MembershipResponse;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import com.example.demo.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class MembershipService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;
    @Transactional
    public MembershipResponse addMember(MembershipRequest request) {
        checkAdmin(request.getGroupId());
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new NotFoundException("Group not found"));
        Membership membership = Membership.builder()
                . user(user)
                .group(group)
                .role(request.getRole())
                .build();
        membershipRepository.save(membership);
        return membershipMapper.toResponse(membership);
    }
    public void checkAdmin(UUID groupId) {
        UUID userId = getCurrentUser().getId();
        Membership membership = membershipRepository
                .findByUserIdAndGroupId(userId, groupId)
                .orElseThrow(() -> new NotFoundException("Not a member of this group"));
        if(membership.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Access denied");
        }
    }
    public void checkMember(UUID groupId) {
        UUID userId = getCurrentUser().getId();
        membershipRepository
                .findByUserIdAndGroupId(userId, groupId)
                .orElseThrow(() -> new NotFoundException("Not a member of this group"));
    }
    @Transactional
    public void changeRole(UUID targetUser, UUID groupId, Role newRole) {
        checkAdmin(groupId);
        Membership membership = membershipRepository
                .findByUserIdAndGroupId(targetUser,groupId)
                .orElseThrow(() -> new NotFoundException("Not a member of this group"));
        membership.setRole(newRole);
        membershipRepository.save(membership);
    }

    public List<UserResponse> getGroupMembers(UUID groupId) {
        return membershipRepository.findByGroupId(groupId)
                .stream()
                .map(m -> {
                    UserEntity u = m.getUser();
                    return UserResponse.builder()
                            .id(u.getId())
                            .name(u.getName())
                            .email(u.getEmail())
                            .build();
                })
                .toList();
    }
}
