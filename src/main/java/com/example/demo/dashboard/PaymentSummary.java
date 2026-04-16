package com.example.demo.dashboard;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentSummary {
    private BigDecimal totalOwed;
    private BigDecimal totalPaid;
}
