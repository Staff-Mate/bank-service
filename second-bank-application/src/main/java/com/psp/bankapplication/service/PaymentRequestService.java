package com.psp.bankapplication.service;

import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;


    public PaymentRequest save(PaymentRequest paymentRequest) {
        return paymentRequestRepository.save(paymentRequest);
    }

    public PaymentRequest getPaymentRequestByPaymentId(String paymentId) {
        return paymentRequestRepository.findByPaymentId(paymentId);
    }

}
