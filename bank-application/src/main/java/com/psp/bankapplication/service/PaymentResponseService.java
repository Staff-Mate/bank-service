package com.psp.bankapplication.service;

import com.psp.bankapplication.model.PaymentResponse;
import com.psp.bankapplication.repository.PaymentResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentResponseService {

    @Autowired
    private PaymentResponseRepository paymentResponseRepository;

    public void save(PaymentResponse paymentResponse) {
        paymentResponseRepository.save(paymentResponse);
    }
}
