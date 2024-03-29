package com.pcc.repository;

import com.pcc.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {

    Bank getBankByBankCode(String bankCode);

    Bank getBankByUrl(String url);
}
