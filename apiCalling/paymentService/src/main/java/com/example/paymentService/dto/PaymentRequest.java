package com.example.paymentService.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private String status;
}
