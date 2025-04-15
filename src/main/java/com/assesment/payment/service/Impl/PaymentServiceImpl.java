package com.assesment.payment.service;

import com.assesment.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Autowired;

public class PaymentServiceImpl implements PaymentService{
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    PaymentRepository paymentRepository;
    
    @Autowired
    LoanRepository loanRepository;

    @Override
    public Payment createPayment(Payment payment){
        Optional<Loan> loan = loanRepository.getLoanById(payment.getLoanId());

        if (loan.isPresent()) {
            Loan loanValue = loan.get();

            if(payment.getPaymentAmount() > loanValue.getLoanStatus() == LoanStatus.SETTLED){
                throw new LoanValidationException("Failed to process loan payment as the loan is already settled")
            }
            if(payment.getPaymentAmount() > loanValue.getLoanAmount()){
                throw new LoanValidationException("Failed to process loan payment as the payment amount exceeds the remaining loan
                 balance, please pay outstanding balance of :" + loanValue.getLoanAmount())
            }

            loanValue.setLoanAmount(loanValue.getLoanAmount() - payment.getPaymentAmount())
            if(loanValue.getLoanAmount() == 0){
                loanValue.setLoanStatus(LoanStatus.SETTLED);
            }
            loanRepository.save(loanValue);
            logger.info("Updated Loan amount, payment sucessfully captured: " + payment)
            return payment;
        } else {
           throw new PaymentValidationException("oops the corresponding Loan was not found",HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}