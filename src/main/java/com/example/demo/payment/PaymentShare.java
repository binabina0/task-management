package com.example.demo.payment;

import com.example.demo.common.BaseEntity;
import com.example.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payment_shares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentShare extends BaseEntity {
    @ManyToOne
    private Payment payment;
    @ManyToOne
    private UserEntity user;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
