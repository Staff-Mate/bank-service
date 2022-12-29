package com.pcc.service;

import com.pcc.model.Bank;
import com.pcc.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank getBankByBankCode(String bankCode) {
        return bankRepository.getBankByBankCode(bankCode);
    }

    public Bank getBankByBankUrl(String url) {
        return bankRepository.getBankByUrl(url);
    }

}
