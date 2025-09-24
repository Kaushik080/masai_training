package com.example.orderService.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String productName;
    private int quantity;
}
