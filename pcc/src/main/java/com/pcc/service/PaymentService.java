package com.pcc.service;

import com.pcc.dto.BankResponseDto;
import com.pcc.dto.PccRequestDto;
import com.pcc.model.Bank;
import com.pcc.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Transaction transaction = transactionService.save(bank, pccRequestDto);
            ResponseEntity<BankResponseDto> bankResponseDtoResponseEntity = restTemplate.postForEntity(bank.getUrl() + "", pccRequestDto, BankResponseDto.class);
            BankResponseDto bankResponseDto = bankResponseDtoResponseEntity.getBody();
            if(bankResponseDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            transactionService.update(transaction, bankResponseDto);
            restTemplate.postForEntity(pccRequestDto.getMerchantBankUrl(), bankResponseDto, String.class);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
