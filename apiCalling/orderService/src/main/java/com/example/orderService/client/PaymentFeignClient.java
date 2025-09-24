package com.example.orderService.client;

import com.example.orderService.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment-client", url = "${payment.service.url}")
public interface PaymentFeignClient {

    @GetMapping("/payments/{orderId}")
    PaymentResponse getPaymentByOrderId(@PathVariable Long orderId);
}
