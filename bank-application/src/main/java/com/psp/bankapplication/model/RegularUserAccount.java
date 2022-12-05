package com.psp.bankapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RegularUserAccount extends UserAccount {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "bank_card_id")
    private BankCard bankCard;
}
