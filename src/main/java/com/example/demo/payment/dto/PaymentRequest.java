package com.example.demo.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class PaymentRequest {
    @NotNull
    private UUID groupId;
    @NotNull
    private BigDecimal amount;
    private String description;
    @NotNull
    private List<UUID> participantsIds;
}
