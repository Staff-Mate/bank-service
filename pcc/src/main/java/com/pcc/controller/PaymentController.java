package com.pcc.controller;

import com.pcc.dto.PccRequestDto;
import com.pcc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<?> processPayment(PccRequestDto pccRequestDto) {
        return paymentService.processPayment(pccRequestDto);
    }


}
