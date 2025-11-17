package com.Vicvin.Bookflix.dto;

import lombok.Data;

@Data
public class VerifyPaymentResponse {
    private boolean status;
    private String message;
    private Data data;

    @lombok.Data
    public static class Data {
        private String status;
        private String reference;
        private String authorization_code;
        private String paid_at;
        private String created_at;
    }
}
