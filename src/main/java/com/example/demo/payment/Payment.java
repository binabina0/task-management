package com.example.demo.payment;

import com.example.demo.common.BaseEntity;
import com.example.demo.group.Group;
import com.example.demo.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {
    @ManyToOne
    private Group group;
    @ManyToOne
    @JoinColumn(name = "payer")
    private UserEntity payer;
    @Column(nullable = false)
    private BigDecimal amount;
    private String description;

}
