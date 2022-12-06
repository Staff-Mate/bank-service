package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.BankCardDto;
import com.psp.bankapplication.dto.PaymentResponseDto;
import com.psp.bankapplication.model.*;
import com.psp.bankapplication.repository.RegularUserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpResponse;
import java.time.LocalDate;

@Service
@Slf4j
public class RegularUserAccountService {

    @Autowired
    private RegularUserAccountRepository regularUserAccountRepository;

    @Autowired
    private PaymentRequestService paymentRequestService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RestTemplate restTemplate;

    private final String RECEIVE_PAYMENT_RESPONSE_URL = "http://localhost:9000/bank-card-service/payments/";
    private final String BANK_ERROR_URL = "http://localhost:8080/payment/error";

    public ResponseEntity<?> processPayment(BankCardDto bankCardDto) {
        //TODO: check PAN number (PCC)
        RegularUserAccount regularUserAccount = regularUserAccountRepository.findByBankCard_Pan(bankCardDto.getPan()); //TODO: with hash
        if(regularUserAccount == null){
            log.error("Regular user account does not exist. Invalid bank card pan");
            return new ResponseEntity<>(BANK_ERROR_URL, HttpStatus.BAD_REQUEST);
        }

        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequestByPaymentId(bankCardDto.getPaymentId());

        if(paymentRequest != null){
            if(verifyCardInformation(regularUserAccount.getBankCard(),bankCardDto)){
                boolean isUpdated = updateRegularUserAssets(regularUserAccount, paymentRequest);
                TransactionStatus transactionStatus = isUpdated ? TransactionStatus.SUCCESS : TransactionStatus.FAILED;
                transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);

                PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentRequest, transactionStatus);
                ResponseEntity<String> responseUrl = restTemplate.postForEntity(RECEIVE_PAYMENT_RESPONSE_URL, paymentResponseDto, String.class);
                log.debug("Payment successfully processed. Resulting url given as a response from bank card service: {}", responseUrl);

                return new ResponseEntity<>(responseUrl, HttpStatus.CREATED);
            }else{
                TransactionStatus transactionStatus = TransactionStatus.ERROR;
                transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentRequest, transactionStatus);
                ResponseEntity<String> responseUrl = restTemplate.postForEntity(RECEIVE_PAYMENT_RESPONSE_URL, paymentResponseDto, String.class);
                return new ResponseEntity<>(responseUrl, HttpStatus.BAD_REQUEST);
            }
        } else {
            log.error("Payment request for payment with id {} does not exist.", bankCardDto.getPaymentId());
            return new ResponseEntity<>(BANK_ERROR_URL, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean updateRegularUserAssets(RegularUserAccount regularUserAccount, PaymentRequest paymentRequest) {
        if(regularUserAccount.getAvailableAssets() >= paymentRequest.getAmount()){
            regularUserAccount.setAvailableAssets(regularUserAccount.getAvailableAssets() - paymentRequest.getAmount());
            regularUserAccount.setReservedAssets(regularUserAccount.getReservedAssets() + paymentRequest.getAmount());
            regularUserAccountRepository.save(regularUserAccount);
            log.debug("Regular user assets updated. Regular user account id: {}", regularUserAccount.getId());
            return true;
        }else{
            log.warn("Regular user assets not updated, insufficient funds. Regular user account id: {}", regularUserAccount.getId());
            return false;
        }
    }

    private boolean verifyCardInformation(BankCard bankCard, BankCardDto bankCardDto){
        LocalDate expirationDateDto = LocalDate.of(Integer.parseInt(bankCardDto.getExpirationYear()), Integer.parseInt(bankCardDto.getExpirationMonth()),1);
        return bankCard.getCardHolderName().equals(bankCardDto.getCardHolderName()) &&
                bankCard.getSecurityCode().equals(bankCardDto.getSecurityCode()) &&
                bankCard.getExpirationDate().equals(expirationDateDto) &&
                bankCard.getExpirationDate().isAfter(LocalDate.now());
    }

}
