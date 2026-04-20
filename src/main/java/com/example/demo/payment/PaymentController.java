package com.example.demo.payment;

import com.example.demo.common.response.ApiResponse;
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
    public ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .data(paymentService.createPayment(request))
                .build();
    }
    @GetMapping("/my")
    public ApiResponse<List<PaymentShare>> myDebts() {
        return ApiResponse.<List<PaymentShare>>builder()
                .success(true)
                .data(paymentService.getUserDebts())
                .build();
    }
}