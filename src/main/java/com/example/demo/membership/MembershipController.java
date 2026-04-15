package com.example.demo.membership;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @PostMapping
    public Membership addMember(@RequestParam UUID userId,
                                @RequestParam UUID groupId,
                                @RequestParam Role role) {
        return membershipService.addMember(userId, groupId, role);
    }
}
