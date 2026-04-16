package com.example.demo.membership;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MembershipRepository  extends JpaRepository<Membership, UUID> {
    Optional<Membership> findByUserIdAndGroupId(UUID userId, UUID groupId);
}
