package com.example.demo.group;

import com.example.demo.user.UserEntity;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserService userService;
    private final GroupRepository groupRepository;

    public Group createGroup(String name, String description, UUID userID) {
        UserEntity user = userService.getBYId(userID);
        Group group = Group.builder()
                .name(name)
                .description(description)
                .createdBy(user)
                .build();
        return groupRepository.save(group);
    }
}
