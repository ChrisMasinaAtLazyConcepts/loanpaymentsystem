package com.assesment.payment.api.rest.controller;

import com.assesment.loan.domain.Loan;
import com.assesment.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    public PaymentController() {
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {

        if(payment.getLoanId() == null){
            throw new LoanValidationException("LoanId cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if(payment.getPaymentAmount() == null ){
            throw new LoanValidationException("payment amount cannot be empyty", HttpStatus.BAD_REQUEST);
        }

        try{
            Payment newPayment = paymentService.save(payment);
        }catch(LoanValidationException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(newPayment, HttpStatus.OK);
    }

}