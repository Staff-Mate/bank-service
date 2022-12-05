package com.psp.bankapplication.dto;

import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.model.TransactionStatus;
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
public class PaymentResponseDto {

    private String paymentId;
    private String url;
    private String transactionStatus;
    private String merchantId;
    private String acquirerOrderId;
    private Timestamp acquirerTimestamp;

    public PaymentResponseDto(PaymentRequest paymentRequest, TransactionStatus transactionStatus){
        this.acquirerOrderId = Utils.generateId();
        this.acquirerTimestamp = new Timestamp(System.currentTimeMillis());
        this.paymentId = paymentRequest.getPaymentId();
        this.merchantId = paymentRequest.getMerchantId();
        this.transactionStatus = transactionStatus.toString();
        switch (transactionStatus){
            case ERROR -> {
                this.url = paymentRequest.getErrorUrl();
            }
            case FAILED -> {
                this.url = paymentRequest.getFailedUrl();
            }
            case SUCCESS -> {
                this.url = paymentRequest.getSuccessUrl();
            }
        }
    }
}
