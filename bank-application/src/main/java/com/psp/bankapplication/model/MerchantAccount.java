package com.psp.bankapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MerchantAccount extends UserAccount {

    @Column
    private String merchantId;

    @Column
    private String merchantPassword;
}
