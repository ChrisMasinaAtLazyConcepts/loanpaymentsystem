package com.assesment.loan.service;

import com.assesment.loan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Autowired;

public class LoanServiceImpl implements LoanService{
    
    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    LoanRepository repository;

    @Override
    public Loan createLoan(Loan loan){
        loan.setLoanStatus(LoanStatus.ACTIVE)
        return repository.save(loan);
    }

    @Override
    public Loan getLoanById(Long loanId){
        return repository.getLoanById(loanId);
    }
}