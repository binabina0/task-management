package com.example.demo.membership;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.membership.dto.MembershipRequest;
import com.example.demo.membership.dto.MembershipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @PostMapping
    public ApiResponse<MembershipResponse> addMember(@RequestBody MembershipRequest request) {
        return ApiResponse.<MembershipResponse>builder()
                .success(true)
                .data(membershipService.addMember(request))
                .build();
    }
}
