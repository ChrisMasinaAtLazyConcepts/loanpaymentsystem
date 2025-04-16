package com.assesment.loan.service;

import com.assesment.loan.api.rest.controller.exception.LoanValidationException;
import com.assesment.loan.domain.Loan;
import com.assesment.loan.domain.LoanStatus;
import com.assesment.loan.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoanService  {

    
    private LoanRepository repository;

    public LoanService() {
    }
    
    public LoanService(LoanRepository repository) {
      this.repository=repository;
    }

    public Loan createLoan(Loan loan) {
        loan.setStatus(LoanStatus.ACTIVE);
        return repository.save(loan);
    }

    public Loan getLoanById(Long loanId) {
      Loan loan = repository.findById(loanId).orElse(null);
      if (loan == null) {
         throw new LoanValidationException("Loan not found with id " ,HttpStatus.NOT_FOUND);
      }
      return loan;
    }
}