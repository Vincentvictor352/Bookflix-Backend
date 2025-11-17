package com.Vicvin.Bookflix.service;


import com.Vicvin.Bookflix.dto.InitializePaymentRequest;
import com.Vicvin.Bookflix.dto.InitializePaymentResponse;
import com.Vicvin.Bookflix.dto.VerifyPaymentResponse;

public interface PaymentService {
    InitializePaymentResponse initializePayment(InitializePaymentRequest request);
    VerifyPaymentResponse verifyPayment(String reference);
}
