package com.example.demo.payment;

import com.example.demo.common.exception.NotFoundException;
import com.example.demo.group.Group;
import com.example.demo.group.GroupRepository;
import com.example.demo.membership.MembershipService;
import com.example.demo.payment.dto.PaymentRequest;
import com.example.demo.payment.dto.PaymentResponse;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.example.demo.common.SecurityUtil.getCurrentUser;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final MembershipService membershipService;
    private final GroupRepository groupRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final PaymentShareRepository paymentShareRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        membershipService.checkMember(request.getGroupId());
        UserEntity payer = getCurrentUser();
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new NotFoundException("Group not found"));
        Payment payment = Payment.builder()
                .group(group)
                .payer(payer)
                .amount(request.getAmount())
                .description(request.getDescription())
                .build();
        Payment saved = paymentRepository.save(payment);

        int count = request.getParticipantsIds().size();
        BigDecimal splitAmount  = request.getAmount().divide(BigDecimal.valueOf(count));
        for(UUID userId : request.getParticipantsIds()) {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
            PaymentShare share = PaymentShare.builder()
                    .payment(saved)
                    .user(user)
                    .amount(splitAmount)
                    .status(user.getId().equals(payer.getId())
                            ? PaymentStatus.PAID
                            : PaymentStatus.UNPAID)
                    .build();
            paymentShareRepository.save(share);
        }
        return paymentMapper.toResponse(saved, payer);
    }
    public List<PaymentShare> getUserDebts() {
        UserEntity user = getCurrentUser();
        return paymentShareRepository.findByUserId(user.getId());
    }
}
