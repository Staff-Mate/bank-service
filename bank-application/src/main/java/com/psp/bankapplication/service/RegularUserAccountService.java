package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.BankCardDto;
import com.psp.bankapplication.dto.PaymentResponseDto;
import com.psp.bankapplication.model.*;
import com.psp.bankapplication.repository.RegularUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegularUserAccountService {

    @Autowired
    private RegularUserAccountRepository regularUserAccountRepository;

    @Autowired
    private PaymentRequestService paymentRequestService;

    @Autowired
    private TransactionService transactionService;

    public ResponseEntity<?> processPayment(BankCardDto bankCardDto) {
        //TODO: check PAN number (PCC)
        RegularUserAccount regularUserAccount = regularUserAccountRepository.findByBankCard_Pan(bankCardDto.getPan()); //TODO: with hash
        if(regularUserAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequestByPaymentId(bankCardDto.getPaymentId());

        if(paymentRequest != null){
            if(verifyCardInformation(regularUserAccount.getBankCard(),bankCardDto)){
                boolean isUpdated = updateRegularUserAssets(regularUserAccount, paymentRequest);
                TransactionStatus transactionStatus = isUpdated ? TransactionStatus.SUCCESS : TransactionStatus.FAILED;
                transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentRequest, transactionStatus);
                return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
            }else{
                TransactionStatus transactionStatus = TransactionStatus.ERROR;
                transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentRequest, transactionStatus);
                return new ResponseEntity<>(paymentResponseDto, HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean updateRegularUserAssets(RegularUserAccount regularUserAccount, PaymentRequest paymentRequest) {
        if(regularUserAccount.getAvailableAssets() >= paymentRequest.getAmount()){
            regularUserAccount.setAvailableAssets(regularUserAccount.getAvailableAssets() - paymentRequest.getAmount());
            regularUserAccount.setReservedAssets(regularUserAccount.getReservedAssets() + paymentRequest.getAmount());
            regularUserAccountRepository.save(regularUserAccount);
            return true;
        }else{
            return false;
        }
    }

    private boolean verifyCardInformation(BankCard bankCard, BankCardDto bankCardDto){
        return bankCard.getCardHolderName().equals(bankCardDto.getCardHolderName()) &&
                bankCard.getSecurityCode().equals(bankCardDto.getSecurityCode()) &&
                bankCard.getExpirationDate().equals(bankCardDto.getExpirationDate()) &&
                bankCard.getExpirationDate().after(new Date());
    }

}
