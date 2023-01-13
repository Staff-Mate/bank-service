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
    private String account;

    @Column
    private Double reservedAssets;

    @Column
    private Double availableAssets;

    public UserAccount() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Double getReservedAssets() {
        return reservedAssets;
    }

    public Double getAvailableAssets() {
        return availableAssets;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setReservedAssets(Double reservedAssets) {
        this.reservedAssets = reservedAssets;
    }

    public void setAvailableAssets(Double availableAssets) {
        this.availableAssets = availableAssets;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
