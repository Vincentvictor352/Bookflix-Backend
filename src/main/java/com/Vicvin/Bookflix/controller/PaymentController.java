package com.Vicvin.Bookflix.controller;


import com.Vicvin.Bookflix.dto.InitializePaymentRequest;
import com.Vicvin.Bookflix.dto.InitializePaymentResponse;
import com.Vicvin.Bookflix.dto.VerifyPaymentResponse;
import com.Vicvin.Bookflix.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initialize")
    public InitializePaymentResponse initializePayment(@RequestBody InitializePaymentRequest request) {
        return paymentService.initializePayment(request);
    }

    @GetMapping("/verify/{reference}")
    public VerifyPaymentResponse verifyPayment(@PathVariable String reference) {
        return paymentService.verifyPayment(reference);
    }
}
