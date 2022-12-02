package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.BankCardDto;
import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.model.Transaction;
import com.psp.bankapplication.model.TransactionStatus;
import com.psp.bankapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(BankCardDto bankCardDto, PaymentRequest paymentRequest, TransactionStatus transactionStatus) {
        Transaction transaction = new Transaction();
        transaction.setPan(bankCardDto.getPan());
        transaction.setTransactionStatus(transactionStatus);
        transaction.setPaymentRequest(paymentRequest);
        transactionRepository.save(transaction);
    }
}
