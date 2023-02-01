package com.pcc.controller;

import com.pcc.dto.PccRequestDto;
import com.pcc.service.PaymentService;
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
    public ResponseEntity<?> processPayment(@RequestBody PccRequestDto pccRequestDto) {
        log.debug("POST request received - /payments/. Merchant id: {}, merchant order id: {}, amount: {}",
                pccRequestDto.getPaymentRequest().getMerchantId(), pccRequestDto.getPaymentRequest().getMerchantOrderId(), pccRequestDto.getPaymentRequest().getAmount());
        return paymentService.processPayment(pccRequestDto);
    }


}
