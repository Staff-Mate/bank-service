package com.psp.bankapplication.model;

import com.psp.bankapplication.dto.PccRequestDto;
import com.psp.bankapplication.dto.PccResponseDto;
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
public class PccResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column
    private String acquirerOrderId;

    @Column
    private Timestamp acquirerTimestamp;

    @Column
    private String issuerOrderId;

    @Column
    private Timestamp issuerTimestamp;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "payment_request_id")
    private PaymentRequest paymentRequest;

    public PccResponse(PccRequestDto pccRequestDto, PaymentRequest paymentRequest){
        this.acquirerOrderId = pccRequestDto.getAcquirerOrderId();
        this.acquirerTimestamp = pccRequestDto.getAcquirerTimestamp();
        this.issuerTimestamp = new Timestamp(System.currentTimeMillis());;
        this.issuerOrderId = Utils.generateId();
        this.paymentRequest = paymentRequest;
    }

}
