package com.example.demo.group;

import com.example.demo.group.dto.GroupRequest;
import com.example.demo.group.dto.GroupResponse;
import com.example.demo.membership.Membership;
import com.example.demo.membership.MembershipRepository;
import com.example.demo.membership.Role;
import com.example.demo.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final GroupMapper groupMapper;

    public GroupResponse createGroup(GroupRequest request) {
        UserEntity currentUser = getCurrentUser();
        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(currentUser)
                .build();
        Group saved = groupRepository.save(group);
        Membership membership = Membership.builder()
                .user(currentUser)
                .group(saved)
                .role(Role.ADMIN)
                .build();
        membershipRepository.save(membership);
        return groupMapper.toResponse(saved);
    }

    public List<GroupResponse> getUserGroups() {
        UUID userId = getCurrentUser().getId();
        return membershipRepository.findByUserId(userId)
                .stream()
                .map(m -> {
                    Group g = m.getGroup();
                    return GroupResponse.builder()
                            .id(g.getId())
                            .name(g.getName())
                            .description(g.getDescription())
                            .build();

                })
                .toList();
    }
}
