package com.psp.bankapplication.dto;

import com.psp.bankapplication.model.PccResponse;
import com.psp.bankapplication.model.TransactionStatus;
import com.psp.bankapplication.util.Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PccResponseDto {
    private String transactionStatus;

    private String acquirerOrderId;

    private Timestamp acquirerTimestamp;

    private String issuerOrderId;

    private Timestamp issuerTimestamp;

    public PccResponseDto(PccResponse pccResponse){
        this.acquirerOrderId = pccResponse.getAcquirerOrderId();
        this.acquirerTimestamp = pccResponse.getAcquirerTimestamp();
        this.issuerTimestamp = pccResponse.getIssuerTimestamp();
        this.issuerOrderId = pccResponse.getIssuerOrderId();
    }


}
