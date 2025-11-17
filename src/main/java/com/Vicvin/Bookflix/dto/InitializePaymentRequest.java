package com.Vicvin.Bookflix.dto;

import lombok.Data;

@Data
public class InitializePaymentRequest {
    private String email;
    private int amount; // amount in Kobo (e.g., ₦1000 = 100000)
}
