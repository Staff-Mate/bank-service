package com.psp.bankapplication.repository;

import com.psp.bankapplication.model.PccResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PccResponseRepository extends JpaRepository<PccResponse, UUID> {
}
