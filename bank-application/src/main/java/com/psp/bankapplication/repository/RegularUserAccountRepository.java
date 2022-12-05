
package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.RegularUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegularUserAccountRepository extends UserAccountRepository {
    RegularUserAccount findByBankCard_Pan(String pan);
}