package com.psp.bankapplication.dto;

import com.psp.bankapplication.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
