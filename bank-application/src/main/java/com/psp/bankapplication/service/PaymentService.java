package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.PaymentRequestDto;
import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.model.Transaction;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class PaymentService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentRequestService paymentRequestService;

    public RedirectView acceptPaymentRequest(PaymentRequestDto paymentRequestDto){
        if(accountService.doesMerchantExist(paymentRequestDto.getMerchantId(), paymentRequestDto.getMerchantPassword())){
            PaymentRequest paymentRequest = modelMapper.map(paymentRequestDto, PaymentRequest.class);
            paymentRequest.setPaymentId(String.format("%.0f", (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L)));
            paymentRequestService.save(paymentRequest);
            return new RedirectView("http://localhost:4200/payment/" + paymentRequest.getPaymentId() );
        }
        return new RedirectView(paymentRequestDto.getErrorUrl());
    }
}
