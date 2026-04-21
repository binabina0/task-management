package com.example.demo.group;

import com.example.demo.group.dto.GroupResponse;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public GroupResponse toResponse(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .build();
    }
}
