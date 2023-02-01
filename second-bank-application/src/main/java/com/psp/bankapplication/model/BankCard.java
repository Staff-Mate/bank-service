package com.psp.bankapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column
    private String pan;

    @Column
    private String securityCode;

    @Column
    private LocalDate expirationDate;
}
