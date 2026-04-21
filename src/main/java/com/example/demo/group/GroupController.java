package com.example.demo.group;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.group.dto.GroupRequest;
import com.example.demo.group.dto.GroupResponse;
import com.example.demo.membership.MembershipService;
import com.example.demo.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/groups")
public class GroupController {
    private final GroupService groupService;
    private final MembershipService membershipService;

    @PostMapping
    public ApiResponse<GroupResponse> createGroup(@RequestBody GroupRequest request) {
        return ApiResponse.<GroupResponse>builder()
                .success(true)
                .data(groupService.createGroup(request))
                .build();
    }
    @GetMapping("/my")
    public ApiResponse<List<GroupResponse>> myGroups() {
        return ApiResponse.<List<GroupResponse>>builder()
                .success(true)
                .data(groupService.getUserGroups())
                .build();
    }
    @GetMapping("/{groupId}/members")
    public ApiResponse<List<UserResponse>> getMembers(@PathVariable("groupId") UUID groupId) {
        return ApiResponse.<List<UserResponse>>builder()
                .success(true)
                .data(membershipService.getGroupMembers(groupId))
                .build();
    }
}
