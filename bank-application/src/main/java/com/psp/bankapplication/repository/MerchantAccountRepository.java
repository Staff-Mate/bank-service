package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.MerchantAccount;
import com.psp.bankapplication.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MerchantAccountRepository extends UserAccountRepository {

    MerchantAccount findByMerchantId(String merchantId);
}