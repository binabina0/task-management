package com.example.demo.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentShareRepository extends JpaRepository<PaymentShare, UUID> {
    List<PaymentShare> findByUserId(UUID userId);
}
