package com.example.demo.group;

import com.example.demo.group.dto.GroupRequest;
import com.example.demo.group.dto.GroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public GroupResponse createGroup(@RequestBody GroupRequest request) {
        return groupService.createGroup(request);
    }
}
