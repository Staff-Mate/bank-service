package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.RegularUserAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserAccountRepository extends UserAccountRepository {
    RegularUserAccount findByBankCard_Pan(String pan);
}