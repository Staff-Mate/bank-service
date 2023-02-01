package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.QrPaymentInfoDto;
import com.psp.bankapplication.model.MerchantAccount;
import com.psp.bankapplication.repository.MerchantAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantAccountService {

    @Autowired
    private MerchantAccountRepository merchantAccountRepository;


    public ResponseEntity<?> getMerchantInfo(String id) {
        MerchantAccount merchantAccount = merchantAccountRepository.findByMerchantId(id);
        return new ResponseEntity<>(new QrPaymentInfoDto(merchantAccount.getCompanyName(), merchantAccount.getAccount()),HttpStatus.OK);
    }
}
