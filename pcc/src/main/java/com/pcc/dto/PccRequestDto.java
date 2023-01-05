package com.pcc.dto;

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

}
