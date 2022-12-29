package com.pcc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BankResponseDto {
    private String transactionStatus;

    private String acquirerOrderId;

    private Timestamp acquirerTimestamp;

    private String issuerOrderId;

    private Timestamp issuerTimestamp;
}
