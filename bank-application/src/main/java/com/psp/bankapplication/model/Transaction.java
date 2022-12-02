package com.psp.bankapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    private String merchantId;

    @Column
    private Double amount;

    @Column
    private String merchantOrderId;

    @Column
    private Timestamp merchantTimestamp;

    @Column
    private String pan;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}

