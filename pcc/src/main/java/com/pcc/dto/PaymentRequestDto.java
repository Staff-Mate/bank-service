package com.pcc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto {

    private String merchantId;

    private Double amount;

    private String merchantOrderId;

    private Timestamp merchantTimestamp;

}
