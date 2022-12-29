package com.psp.bankapplication.service;

import com.psp.bankapplication.model.PccResponse;
import com.psp.bankapplication.repository.PccResponseRepository;
import org.springframework.stereotype.Service;

@Service
public class PccResponseService {

    private PccResponseRepository pccResponseRepository;

    public void save(PccResponse pccResponse){
        pccResponseRepository.save(pccResponse);
    }
}
