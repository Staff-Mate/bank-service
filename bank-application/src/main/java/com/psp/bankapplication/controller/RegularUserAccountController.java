package com.psp.bankapplication.controller;

import com.psp.bankapplication.dto.BankCardDto;
import com.psp.bankapplication.service.RegularUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/accounts")
public class RegularUserAccountController {

    @Autowired
    private RegularUserAccountService regularUserAccountService;

    @PostMapping("")
    public RedirectView processPayment(@RequestBody BankCardDto bankCardDto){
        return regularUserAccountService.processPayment(bankCardDto);
    }

}
