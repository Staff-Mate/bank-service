package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.BankCardDto;
import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.model.Transaction;
import com.psp.bankapplication.model.TransactionStatus;
import com.psp.bankapplication.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(BankCardDto bankCardDto, PaymentRequest paymentRequest, TransactionStatus transactionStatus) {
        Transaction transaction = new Transaction();
        transaction.setPan(bankCardDto.getPan());
        transaction.setTransactionStatus(transactionStatus);
        transaction.setPaymentRequest(paymentRequest);
        transactionRepository.save(transaction);

        log.debug("Transaction with id {} saved. Payment request id: {}, payment id: {}", transaction.getId(),
                transaction.getPaymentRequest().getId(), transaction.getPaymentRequest().getPaymentId());
    }
}
