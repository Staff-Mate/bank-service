package com.psp.bankapplication.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BankCardDto {


    private String cardHolderName;
    private String pan;
    private String securityCode;
    private Date expirationDate;

    private String paymentId;
}
