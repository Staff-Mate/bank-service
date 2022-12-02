package com.psp.bankapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String cardHolderName;

    @Column(length = 16)
    private String pan;

    @Column
    private String securityCode;

    @Column
    private Date expirationDate;
}
