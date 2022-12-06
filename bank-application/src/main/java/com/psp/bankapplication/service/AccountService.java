package com.psp.bankapplication.service;

import com.psp.bankapplication.model.MerchantAccount;
import com.psp.bankapplication.repository.MerchantAccountRepository;
import com.psp.bankapplication.repository.RegularUserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private MerchantAccountRepository merchantAccountRepository;

    @Autowired
    private RegularUserAccountRepository regularUserAccountRepository;

    public Boolean doesMerchantExist(String merchantId, String merchantPassword){
        MerchantAccount merchantAccount = merchantAccountRepository.findByMerchantId(merchantId);
        if (merchantAccount == null){
            log.warn("Merchant with id: {} does not exist!", merchantId);
            return false;
        }
        return merchantAccount.getMerchantPassword().equals(merchantPassword); //TODO: encryption?
    }
}
