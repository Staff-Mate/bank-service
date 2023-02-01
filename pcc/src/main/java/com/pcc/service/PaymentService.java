package com.pcc.service;

import com.pcc.dto.BankResponseDto;
import com.pcc.dto.PccRequestDto;
import com.pcc.model.Bank;
import com.pcc.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private BankService bankService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> processPayment(PccRequestDto pccRequestDto) {
        Bank bank = bankService.getBankByBankCode(pccRequestDto.getCardInfo().getPan().substring(0, 6));
        if (bank == null) {
            log.error("Error during payment processing, merchant order id: {}. Bank with bank code: {} does not exist.",
                    pccRequestDto.getPaymentRequest().getMerchantOrderId(), pccRequestDto.getCardInfo().getPan().substring(0, 6));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Transaction transaction = transactionService.save(bank, pccRequestDto);
            ResponseEntity<BankResponseDto> bankResponseDtoResponseEntity = restTemplate.postForEntity(bank.getUrl() + "/accounts/pcc", pccRequestDto, BankResponseDto.class);
            BankResponseDto bankResponseDto = bankResponseDtoResponseEntity.getBody();
            if(bankResponseDto == null){
                log.error("Error during payment processing, merchant order id: {}.",
                        pccRequestDto.getPaymentRequest().getMerchantOrderId());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            log.debug("Payment request processed. Merchant id : {}, merchant Order id: {}",
                    pccRequestDto.getPaymentRequest().getMerchantId(), pccRequestDto.getPaymentRequest().getMerchantOrderId());
            transactionService.update(transaction, bankResponseDto);
            return new ResponseEntity<>(bankResponseDto, HttpStatus.OK);
        }
    }
}
