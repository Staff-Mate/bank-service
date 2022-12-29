package com.psp.bankapplication.controller;

import com.psp.bankapplication.dto.PaymentRequestDto;
import com.psp.bankapplication.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<?> acceptPaymentRequest(@RequestBody PaymentRequestDto paymentRequestDto) {
        log.debug("POST request received - /payments/. Merchant id: {}, merchant order id: {}, amount: {}",
                paymentRequestDto.getMerchantId(), paymentRequestDto.getMerchantOrderId(), paymentRequestDto.getAmount());
        return paymentService.acceptPaymentRequest(paymentRequestDto);
    }

}
