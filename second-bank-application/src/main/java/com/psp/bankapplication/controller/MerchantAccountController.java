package com.psp.bankapplication.controller;

import com.psp.bankapplication.service.MerchantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchants")
public class MerchantAccountController {

    @Autowired
    private MerchantAccountService merchantAccountService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMerchantInfo(@PathVariable String id) {
        return merchantAccountService.getMerchantInfo(id);
    }
}
