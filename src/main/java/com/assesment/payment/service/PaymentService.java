package com.assesment.payment.service;

import com.assesment.loan.api.rest.controller.exception.LoanValidationException;
import com.assesment.loan.domain.Loan;
import com.assesment.loan.domain.LoanStatus;
import com.assesment.loan.repository.LoanRepository;
import com.assesment.payment.api.rest.controller.exception.PaymentValidationException;
import com.assesment.payment.domain.Payment;
import com.assesment.payment.repository.PaymentRepository;
import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    LoanRepository loanRepository;

    public PaymentService(  ){                       
    }

    public Payment createPayment(Payment payment){
        Loan loan = loanRepository.findByLoanId(payment.getLoanId()).get();
        if (loan != null) {
            if (payment.getPaymentAmount().compareTo(loan.getLoanAmount()) > 0) {
                throw new PaymentValidationException("Failed to process loan payment as the payment amount exceeds the remaining loan" +
                 "balance, please pay outstanding balance of :" + loan.getLoanAmount(),HttpStatus.BAD_REQUEST);
            }
            if(loan.getStatus() == LoanStatus.SETTLED){
                throw new PaymentValidationException("Failed to process loan payment as the loan is already settled",HttpStatus.BAD_REQUEST);
            }

            loan.setLoanAmount(loan.getLoanAmount().subtract(payment.getPaymentAmount()));
            if (loan.getLoanAmount().compareTo(BigDecimal.ZERO) == 0) {
                loan.setStatus(LoanStatus.SETTLED);
            }
            loanRepository.save(loan);
            logger.info("Updated Loan amount, payment sucessfully captured: " + payment);
            return payment;
        } else {
           throw new PaymentValidationException("oops the corresponding Loan was not found",HttpStatus.BAD_REQUEST);
        }
    }


}