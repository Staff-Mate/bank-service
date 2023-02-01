package com.psp.bankapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String pan;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "payment_request_id")
    private PaymentRequest paymentRequest;
}

