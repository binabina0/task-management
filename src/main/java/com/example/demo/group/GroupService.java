package com.example.demo.group;

import com.example.demo.group.dto.GroupRequest;
import com.example.demo.group.dto.GroupResponse;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserService userService;
    private final GroupRepository groupRepository;

    public GroupResponse createGroup(GroupRequest request) {
        UserEntity currentUser = getCurrentUser();
        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(currentUser)
                .build();
        Group saved = groupRepository.save(group);
        return GroupResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getName())
                .build();
    }
}
