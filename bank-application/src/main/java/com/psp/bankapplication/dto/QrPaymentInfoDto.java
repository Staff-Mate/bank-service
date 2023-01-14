package com.psp.bankapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrPaymentInfoDto {
    private String merchantCompanyName;
    private String merchantAccountNumber;
}
