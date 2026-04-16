package com.example.demo.payment;

import com.example.demo.payment.dto.PaymentRequest;
import com.example.demo.payment.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }
    @GetMapping("/my")
    public List<PaymentShare> myDebts() {
        return paymentService.getUserDebts();
    }
}