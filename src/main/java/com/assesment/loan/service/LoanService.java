package com.assesment.loan.service;

import org.springframework.stereotype.Service;
import com.assesment.loan.domain.*;

@Service
public interface LoanService{
    Loan createLoan(Loan loan);
    Loan getLoanById(Long loanId);
}