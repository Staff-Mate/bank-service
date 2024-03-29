package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.*;
import com.psp.bankapplication.model.*;
import com.psp.bankapplication.repository.PccResponseRepository;
import com.psp.bankapplication.repository.RegularUserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private BankService bankService;

    @Autowired
    private PaymentResponseService paymentResponseService;

    @Autowired
    private PccResponseService pccResponseService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    private final String RECEIVE_CARD_PAYMENT_RESPONSE_URL = "http://localhost:9000/bank-card-service/payments/";
    private final String RECEIVE_QR_PAYMENT_RESPONSE_URL = "http://localhost:9000/qr-code-service/payments/";
    private final String BANK_ERROR_URL = "http://localhost:8080/error";

    private final String PCC_URL = "http://localhost:8090/payments/";


    public ResponseEntity<?> processPayment(BankCardDto bankCardDto) {
        PaymentRequest paymentRequest = paymentRequestService.getPaymentRequestByPaymentId(bankCardDto.getPaymentId());

        if (paymentRequest == null) {
            log.error("Payment request for payment with id {} does not exist.", bankCardDto.getPaymentId());
            return new ResponseEntity<>(BANK_ERROR_URL, HttpStatus.BAD_REQUEST);
        } else {

            if(paymentRequest.getProcessed()){
                log.error("Payment request for payment with id {} is already processed.", bankCardDto.getPaymentId());
                return new ResponseEntity<>(BANK_ERROR_URL, HttpStatus.BAD_REQUEST);
            }

            String path = getRequestPath(paymentRequest);
            if (bankCardDto.getPan().substring(0, 6).equals(bankService.getBankCode())) {
                return reserveUserAssets(bankCardDto, paymentRequest);
            } else {
                PaymentRequestDto paymentRequestDto = modelMapper.map(paymentRequest, PaymentRequestDto.class);
                PccRequestDto pccRequestDto = new PccRequestDto(paymentRequestDto, bankCardDto);
                pccRequestDto.setMerchantBankUrl(bankService.getBankUrl());
                log.debug("Payment redirected to pcc. Payment id: {}", paymentRequest.getPaymentId());
                ResponseEntity<PccResponseDto> response = restTemplate.postForEntity(PCC_URL, pccRequestDto, PccResponseDto.class);
                PccResponseDto pccResponseDto = response.getBody();
                pccResponseService.save(modelMapper.map(pccResponseDto, PccResponse.class));

                PaymentResponse paymentResponse = new PaymentResponse(paymentRequest);
                paymentResponseService.save(paymentResponse);
                if(pccResponseDto == null){
                    transactionService.saveTransaction(bankCardDto, paymentRequest, TransactionStatus.ERROR);
                    PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentResponse, TransactionStatus.ERROR);

                    restTemplate.postForEntity(path, paymentResponseDto, String.class);
                    return new ResponseEntity<>(paymentRequest.getErrorUrl(), HttpStatus.OK);
                }else{
                    TransactionStatus transactionStatus = TransactionStatus.valueOf(pccResponseDto.getTransactionStatus());
                    transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);

                    String url = "";
                    if(transactionStatus == TransactionStatus.SUCCESS){
                        url = paymentRequest.getSuccessUrl();
                    }else if(transactionStatus == TransactionStatus.FAILED){
                        url = paymentRequest.getFailedUrl();
                    }else{
                        url = paymentRequest.getErrorUrl();
                    }

                    PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentResponse, transactionStatus);
                    restTemplate.postForEntity(path, paymentResponseDto, String.class);

                    paymentRequest.setProcessed(true);
                    paymentRequestService.save(paymentRequest);
                    return new ResponseEntity<>(url, HttpStatus.OK);
                }
            }
        }
    }

    private String getRequestPath(PaymentRequest paymentRequest) {
        String path = RECEIVE_QR_PAYMENT_RESPONSE_URL;
        if(paymentRequest.getIsBankCardPayment()){
            path = RECEIVE_CARD_PAYMENT_RESPONSE_URL;
        }
        return path;
    }

    public ResponseEntity<?> processPCCPayment(PccRequestDto pccRequestDto) {
        PaymentRequest paymentRequest = modelMapper.map(pccRequestDto.getPaymentRequest(), PaymentRequest.class);
        RegularUserAccount regularUserAccount = findRegularUserAccountByPan(pccRequestDto.getCardInfo().getPan());
        if (regularUserAccount == null) {
            log.error("Regular user account does not exist. Invalid bank card pan. Merchant order id: {}",
                    pccRequestDto.getPaymentRequest().getMerchantOrderId());
            return new ResponseEntity<>(BANK_ERROR_URL, HttpStatus.BAD_REQUEST);
        }

        paymentRequest = paymentRequestService.save(paymentRequest);
        PccResponse pccResponse = new PccResponse(pccRequestDto, paymentRequest);
        pccResponseService.save(pccResponse);
        PccResponseDto pccResponseDto = new PccResponseDto(pccResponse);
        if (verifyCardInformation(regularUserAccount.getBankCard(), pccRequestDto.getCardInfo())) {
            boolean isUpdated = updateRegularUserAssets(regularUserAccount, paymentRequest);
            TransactionStatus transactionStatus = isUpdated ? TransactionStatus.SUCCESS : TransactionStatus.FAILED;
            transactionService.saveTransaction(pccRequestDto.getCardInfo(), paymentRequest, transactionStatus);
            pccResponseDto.setTransactionStatus(transactionStatus.toString());
            return new ResponseEntity<>(pccResponseDto, HttpStatus.OK);
        } else {
            TransactionStatus transactionStatus = TransactionStatus.ERROR;
            transactionService.saveTransaction(pccRequestDto.getCardInfo(), paymentRequest, transactionStatus);
            pccResponseDto.setTransactionStatus(transactionStatus.toString());
            return new ResponseEntity<>(pccResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<?> reserveUserAssets(BankCardDto bankCardDto, PaymentRequest paymentRequest) {
        RegularUserAccount regularUserAccount = findRegularUserAccountByPan(bankCardDto.getPan());
        String path = getRequestPath(paymentRequest);
        if (regularUserAccount == null) {
            log.error("Regular user account does not exist. Invalid bank card pan. Merchant order id: {}",
                    paymentRequest.getMerchantOrderId());
            return new ResponseEntity<>(BANK_ERROR_URL, HttpStatus.BAD_REQUEST);
        }

        PaymentResponse paymentResponse = new PaymentResponse(paymentRequest);
        paymentResponseService.save(paymentResponse);
        if (verifyCardInformation(regularUserAccount.getBankCard(), bankCardDto)) {
            boolean isUpdated = updateRegularUserAssets(regularUserAccount, paymentRequest);
            TransactionStatus transactionStatus = isUpdated ? TransactionStatus.SUCCESS : TransactionStatus.FAILED;
            transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);


            PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentResponse, transactionStatus);
            ResponseEntity<String> responseUrl = restTemplate.postForEntity(path, paymentResponseDto, String.class);
            log.debug("Payment successfully processed. Resulting url given as a response from bank card service: {}", responseUrl);

            paymentRequest.setProcessed(true);
            paymentRequestService.save(paymentRequest);
            return new ResponseEntity<>(responseUrl, HttpStatus.CREATED);
        } else {
            TransactionStatus transactionStatus = TransactionStatus.ERROR;
            transactionService.saveTransaction(bankCardDto, paymentRequest, transactionStatus);
            PaymentResponseDto paymentResponseDto = new PaymentResponseDto(paymentResponse, transactionStatus);
            ResponseEntity<String> responseUrl = restTemplate.postForEntity(path, paymentResponseDto, String.class);
            log.debug("Error during payment process. Invalid bank card information for user with id: {}", regularUserAccount.getId());


            paymentRequest.setProcessed(true);
            paymentRequestService.save(paymentRequest);
            return new ResponseEntity<>(responseUrl, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean updateRegularUserAssets(RegularUserAccount regularUserAccount, PaymentRequest paymentRequest) {
        if (regularUserAccount.getAvailableAssets() >= paymentRequest.getAmount()) {
            regularUserAccount.setAvailableAssets(regularUserAccount.getAvailableAssets() - paymentRequest.getAmount());
            regularUserAccount.setReservedAssets(regularUserAccount.getReservedAssets() + paymentRequest.getAmount());
            regularUserAccountRepository.save(regularUserAccount);
            log.debug("Regular user assets updated. Regular user account id: {}", regularUserAccount.getId());
            return true;
        } else {
            log.warn("Regular user assets not updated, insufficient funds. Regular user account id: {}", regularUserAccount.getId());
            return false;
        }
    }

    private boolean verifyCardInformation(BankCard bankCard, BankCardDto bankCardDto) {
        LocalDate expirationDateDto = LocalDate.of(Integer.parseInt(bankCardDto.getExpirationYear()), Integer.parseInt(bankCardDto.getExpirationMonth()), 1);
        return bankCard.getCardHolderName().equals(bankCardDto.getCardHolderName()) &&
                passwordEncoder.matches(bankCardDto.getSecurityCode(), bankCard.getSecurityCode()) &&
                bankCard.getExpirationDate().equals(expirationDateDto) &&
                bankCard.getExpirationDate().isAfter(LocalDate.now());
    }

    private RegularUserAccount findRegularUserAccountByPan(String pan) {
        for (RegularUserAccount userAccount : regularUserAccountRepository.getAll()) {
            if (passwordEncoder.matches(pan, userAccount.getBankCard().getPan())) {
                return userAccount;
            }
        }
        return null;
    }

}
