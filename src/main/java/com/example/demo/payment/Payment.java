package com.example.demo.payment;

import com.example.demo.common.BaseEntity;
import com.example.demo.group.Group;
import com.example.demo.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private UserEntity payer;
    @Column(nullable = false)
    private BigDecimal amount;
    private String description;

}
