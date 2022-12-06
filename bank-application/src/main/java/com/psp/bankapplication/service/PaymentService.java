package com.psp.bankapplication.service;

import com.psp.bankapplication.dto.PaymentRequestDto;
import com.psp.bankapplication.model.PaymentRequest;
import com.psp.bankapplication.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentRequestService paymentRequestService;

    public ResponseEntity<?> acceptPaymentRequest(PaymentRequestDto paymentRequestDto){
        if(accountService.doesMerchantExist(paymentRequestDto.getMerchantId(), paymentRequestDto.getMerchantPassword())){
            PaymentRequest paymentRequest = modelMapper.map(paymentRequestDto, PaymentRequest.class);
            paymentRequest.setPaymentId(String.format("%.0f", (Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L)));

            paymentRequestService.save(paymentRequest);
            log.debug("Payment request accepted. Payment request id: {}, payment id: {}, merchant id: {}",
                    paymentRequest.getId(), paymentRequest.getPaymentId(), paymentRequest.getMerchantId());

            return new ResponseEntity<>("http://localhost:4000/payment/" + paymentRequest.getPaymentId(), HttpStatus.ACCEPTED);
        }
        log.error("Payment request rejected - merchant credentials invalid. Merchant id: {} ", paymentRequestDto.getMerchantId());
        return new ResponseEntity<>(paymentRequestDto.getErrorUrl(), HttpStatus.BAD_REQUEST);
    }
}
