package com.psp.bankapplication.controller;

import com.psp.bankapplication.dto.PaymentRequestDto;
import com.psp.bankapplication.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<?> acceptPaymentRequest(@RequestBody PaymentRequestDto paymentRequestDto){
        return paymentService.acceptPaymentRequest(paymentRequestDto);
    }

}
