package com.psp.bankapplication.service;

import com.psp.bankapplication.model.MerchantAccount;
import com.psp.bankapplication.repository.MerchantAccountRepository;
import com.psp.bankapplication.repository.RegularUserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private MerchantAccountRepository merchantAccountRepository;

    @Autowired
    private RegularUserAccountRepository regularUserAccountRepository;

    public Boolean doesMerchantExist(String merchantId, String merchantPassword){
        MerchantAccount merchantAccount = merchantAccountRepository.findByMerchantId(merchantId);
        return merchantAccount.getMerchantPassword().equals(merchantPassword); //TODO: encryption?
    }
}
