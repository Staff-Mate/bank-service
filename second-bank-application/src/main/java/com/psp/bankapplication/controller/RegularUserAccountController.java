package com.psp.bankapplication.controller;

import com.psp.bankapplication.dto.BankCardDto;
import com.psp.bankapplication.dto.PccRequestDto;
import com.psp.bankapplication.service.RegularUserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class RegularUserAccountController {

    @Autowired
    private RegularUserAccountService regularUserAccountService;

    @PostMapping("/")
    public ResponseEntity<?> processPayment(@RequestBody BankCardDto bankCardDto) {
        log.debug("POST request received - /accounts/. Card holder name: {}, payment id: {}",
                bankCardDto.getCardHolderName(), bankCardDto.getPaymentId());
        return regularUserAccountService.processPayment(bankCardDto);
    }

    @PostMapping("/pcc")
    public ResponseEntity<?> processPCCPayment(@RequestBody PccRequestDto pccRequestDto) {
        log.debug("POST request received - /accounts/pcc. Card holder name: {}, payment id: {}",
                pccRequestDto.getCardInfo().getCardHolderName(), pccRequestDto.getCardInfo().getPaymentId());
        return regularUserAccountService.processPCCPayment(pccRequestDto);
    }
}
