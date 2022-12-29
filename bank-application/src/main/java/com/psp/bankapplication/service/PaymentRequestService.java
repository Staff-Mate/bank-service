package com.psp.bankapplication.service;

import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;


    public void save(PaymentRequest paymentRequest) {
        paymentRequestRepository.save(paymentRequest);
    }

    public PaymentRequest getPaymentRequestByPaymentId(String paymentId) {
        return paymentRequestRepository.findByPaymentId(paymentId);
    }

}
