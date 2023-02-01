package com.psp.bankapplication.dto;

import com.psp.bankapplication.util.Utils;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PccRequestDto {

    private BankCardDto cardInfo;
    private PaymentRequestDto paymentRequest;
    private String acquirerOrderId;
    private Timestamp acquirerTimestamp;
    private String merchantBankUrl;


    public PccRequestDto(PaymentRequestDto paymentRequestDto, BankCardDto bankCardDto) {
        this.acquirerOrderId = Utils.generateId();
        this.acquirerTimestamp = new Timestamp(System.currentTimeMillis());
        this.paymentRequest = paymentRequestDto;
        this.cardInfo = bankCardDto;
    }
}
