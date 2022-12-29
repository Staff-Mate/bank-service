package com.psp.bankapplication.service;

import com.psp.bankapplication.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public String getBankCode() {
        return bankRepository.getBankByBankName("UniCredit Bank").getBankCode();
    }
}
