package com.psp.bankapplication.dto;

import com.psp.bankapplication.model.PaymentResponse;
import com.psp.bankapplication.model.TransactionStatus;
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

    public PaymentResponseDto(PaymentResponse paymentResponse, TransactionStatus transactionStatus) {
        this.acquirerOrderId = paymentResponse.getAcquirerOrderId();
        this.acquirerTimestamp = paymentResponse.getAcquirerTimestamp();
        this.paymentId = paymentResponse.getPaymentRequest().getPaymentId();
        this.merchantId = paymentResponse.getPaymentRequest().getMerchantId();
        this.transactionStatus = transactionStatus.toString();
        switch (transactionStatus) {
            case ERROR -> {
                this.url = paymentResponse.getPaymentRequest().getErrorUrl();
            }
            case FAILED -> {
                this.url = paymentResponse.getPaymentRequest().getFailedUrl();
            }
            case SUCCESS -> {
                this.url = paymentResponse.getPaymentRequest().getSuccessUrl();
            }
        }
    }
}
