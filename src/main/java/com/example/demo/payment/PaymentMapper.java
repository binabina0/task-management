package com.example.demo.payment;

import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentResponse toResponse(Payment payment, UserEntity payer) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .description(payment.getDescription())
                .payerName(payer.getName())
                .build();
    }
}
