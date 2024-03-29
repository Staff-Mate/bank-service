package com.psp.bankapplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto {

    private String merchantId;

    private String merchantPassword;

    private Double amount;

    private String merchantOrderId;

    private Timestamp merchantTimestamp;

    private Boolean isBankCardPayment;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
