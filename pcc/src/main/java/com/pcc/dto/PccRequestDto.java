package com.pcc.dto;

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

}
