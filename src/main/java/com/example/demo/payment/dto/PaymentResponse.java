package com.example.demo.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PaymentResponse {
    private UUID id;
    private BigDecimal amount;
    private String description;
    private String payerName;
}
