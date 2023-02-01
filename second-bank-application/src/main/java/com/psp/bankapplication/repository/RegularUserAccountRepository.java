package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.RegularUserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RegularUserAccountRepository extends UserAccountRepository {

    @Query("SELECT rua FROM RegularUserAccount rua")
    List<RegularUserAccount> getAll();
}