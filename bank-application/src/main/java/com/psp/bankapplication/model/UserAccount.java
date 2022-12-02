package com.psp.bankapplication.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Double reservedAssets;

    @Column
    private Double availableAssets;
}
