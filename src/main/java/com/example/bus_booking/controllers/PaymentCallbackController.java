package com.example.bus_booking.controllers;

import com.example.bus_booking.entities.PaymentCallBack;
import com.example.bus_booking.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RestController
@RequestMapping("/api/payment/callback")
public class PaymentCallbackController {
    private final OrderService orderService;

    public PaymentCallbackController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(@RequestBody PaymentCallBack paymentCallback) {
        try {
            if (!validateSignature(paymentCallback)) {
                return new ResponseEntity<>("Invalid signature", HttpStatus.FORBIDDEN);
            }
            orderService.updateOrderStatus(paymentCallback);
            return new ResponseEntity<>("Payment confirmed", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing payment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateSignature(PaymentCallBack paymentCallback) {
        String data = paymentCallback.getOrderId() + ":" + paymentCallback.getPaymentId();
        String computedSignature = computeHmacSHA256(data, "SECRET_KEY");
        return computedSignature.equals(paymentCallback.getSignature());
    }

    private String computeHmacSHA256(String data, String secretKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка вычисления подписи", e);
        }
    }
}
