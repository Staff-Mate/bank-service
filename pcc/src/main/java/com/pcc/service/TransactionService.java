package com.pcc.service;

import com.pcc.dto.BankResponseDto;
import com.pcc.dto.PccRequestDto;
import com.pcc.model.Bank;
import com.pcc.model.Transaction;
import com.pcc.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankService bankService;

    public Transaction save(Bank customerBank, PccRequestDto pccRequestDto){
        Transaction transaction = new Transaction(pccRequestDto);
        transaction.setCustomerBank(customerBank);
        transaction.setMerchantBank(bankService.getBankByBankUrl(pccRequestDto.getMerchantBankUrl()));
        return transactionRepository.save(transaction);
    }

    public Transaction update(Transaction transaction, BankResponseDto bankResponseDto) {
        transaction.setIssuerOrderId(bankResponseDto.getIssuerOrderId());
        transaction.setIssuerTimestamp(bankResponseDto.getIssuerTimestamp());
        transaction.setTransactionStatus(bankResponseDto.getTransactionStatus());
        return transaction;
    }
}
