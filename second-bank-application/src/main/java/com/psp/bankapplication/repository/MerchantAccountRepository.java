package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.MerchantAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantAccountRepository extends UserAccountRepository {

    MerchantAccount findByMerchantId(String merchantId);
}