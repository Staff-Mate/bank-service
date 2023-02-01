package com.psp.bankapplication.model;

import com.psp.bankapplication.util.Utils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PaymentResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String acquirerOrderId;

    @Column
    private Timestamp acquirerTimestamp;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "payment_request_id")
    private PaymentRequest paymentRequest;

    public PaymentResponse(PaymentRequest paymentRequest) {
        this.acquirerOrderId = Utils.generateId();
        this.acquirerTimestamp = new Timestamp(System.currentTimeMillis());
        this.paymentRequest = paymentRequest;
    }
}
