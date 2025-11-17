package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.dto.InitializePaymentRequest;
import com.Vicvin.Bookflix.dto.InitializePaymentResponse;
import com.Vicvin.Bookflix.dto.VerifyPaymentResponse;
import com.Vicvin.Bookflix.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${paystack.secret-key}")
    private String secretKey;

    @Value("${paystack.base-url}")
    private String baseUrl;

    @Value("${paystack.callback-url}")
    private String callbackUrl;

    private final RestTemplate restTemplate;

    // Constructor injection ensures Spring can manage RestTemplate
    public PaymentServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public InitializePaymentResponse initializePayment(InitializePaymentRequest request) {
        String url = baseUrl + "/transaction/initialize";

        Map<String, Object> body = new HashMap<>();
        body.put("email", request.getEmail());
        body.put("amount", request.getAmount());
        body.put("callback_url", callbackUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(secretKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<InitializePaymentResponse> response =
                restTemplate.postForEntity(url, entity, InitializePaymentResponse.class);

        return response.getBody();
    }

    @Override
    public VerifyPaymentResponse verifyPayment(String reference) {
        String url = baseUrl + "/transaction/verify/" + reference;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(secretKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<VerifyPaymentResponse> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, VerifyPaymentResponse.class);

        return response.getBody();
    }
}
