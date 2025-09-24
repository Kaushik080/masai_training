package com.example.orderService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Long OrderId;
    private String productName;
    private int quantity;
    private String paymentStatus;
}
