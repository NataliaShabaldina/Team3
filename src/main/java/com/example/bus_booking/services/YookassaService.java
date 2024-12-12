package com.example.bus_booking.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class YookassaService {
    private final RestTemplate restTemplate;

    private static final String API_URL = "https://api.yookassa.ru/v3/payments";
    private static final String SHOP_ID = "shop_id";
    private static final String SECRET_KEY = "secret_key";

    public YookassaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createPayment(BigDecimal amount, String returnUrl) {
        Map<String, Object> paymentRequest = createPaymentRequest(amount, returnUrl);
        return processPaymentRequest(paymentRequest);
    }

    private Map<String, Object> createPaymentRequest(BigDecimal amount, String returnUrl) {
        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("amount", Map.of("value", amount, "currency", "RUB"));
        paymentRequest.put("confirmation", Map.of("type", "redirect", "return_url", returnUrl));
        paymentRequest.put("capture", true);
        paymentRequest.put("description", "Оплата аренды автобуса");
        return paymentRequest;
    }

    private String processPaymentRequest(Map<String, Object> paymentRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(SHOP_ID, SECRET_KEY);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(paymentRequest, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, request, Map.class);
        Map responseBody = response.getBody();
        return (String) ((Map) responseBody.get("confirmation")).get("confirmation_url");
    }

}
