package com.pcc.service;

import com.pcc.dto.PccRequestDto;
import com.pcc.model.Bank;
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
    private RestTemplate restTemplate;

    public ResponseEntity<?> processPayment(PccRequestDto pccRequestDto) {
        Bank bank = bankService.getBankByBankCode(pccRequestDto.getCardInfo().getPan().substring(0, 6));
        if (bank == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            // ResponseEntity<String> responseUrl = restTemplate.postForEntity(bank.getUrl() + "", paymentResponseDto, String.class);
            // ResponseEntity<String> responseUrl2 = restTemplate.postForEntity(bank.getUrl() + "", paymentResponseDto, String.class);
            return new ResponseEntity<>(HttpStatus.OK);

        }
    }
}
