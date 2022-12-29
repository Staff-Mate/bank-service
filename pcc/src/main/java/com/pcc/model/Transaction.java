package com.pcc.model;

import com.pcc.dto.PccRequestDto;
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
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private Double amount;

    @Column
    private String merchantId;

    @Column
    private String merchantOrderId;

    @Column
    private Timestamp merchantTimestamp;

    @Column
    private String acquirerOrderId;

    @Column
    private Timestamp acquirerTimestamp;

    @Column
    private String issuerOrderId;

    @Column
    private Timestamp issuerTimestamp;

    @Column
    private String transactionStatus;


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "merchant_bank_id")
    private Bank merchantBank;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "customer_bank_id")
    private Bank customerBank;

    public Transaction(PccRequestDto pccRequestDto){
        this.amount = pccRequestDto.getPaymentRequest().getAmount();
        this.merchantId = pccRequestDto.getPaymentRequest().getMerchantId();
        this.merchantOrderId = pccRequestDto.getPaymentRequest().getMerchantOrderId();
        this.merchantTimestamp = pccRequestDto.getPaymentRequest().getMerchantTimestamp();
        this.acquirerOrderId = pccRequestDto.getAcquirerOrderId();
        this.acquirerTimestamp = pccRequestDto.getAcquirerTimestamp();
    }
}
