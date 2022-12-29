package com.psp.bankapplication.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankCardDto {

    private String cardHolderName;
    private String pan;
    private String securityCode;
    private String expirationMonth;
    private String expirationYear;
    private String paymentId;
}
