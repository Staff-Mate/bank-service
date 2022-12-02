package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, UUID> {
    PaymentRequest findByPaymentId(String paymentId);
}