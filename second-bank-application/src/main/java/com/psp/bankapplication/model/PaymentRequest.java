package com.psp.bankapplication.model;

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
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String paymentId;

    @Column
    private String merchantId;

    @Column
    private Double amount;

    @Column
    private String merchantOrderId;

    @Column
    private Timestamp merchantTimestamp;

    @Column
    private Boolean isBankCardPayment;

    @Column
    private String successUrl;

    @Column
    private String failedUrl;

    @Column
    private String errorUrl;

    @Column
    private Boolean processed;

}

