package com.example.demo.group;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public Group createGroup(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam UUID userId){
        return groupService.createGroup(name, description, userId);
    }
}
