package com.assesment.loan.service;

import com.assesment.loan.api.rest.controller.exception.LoanValidationException;
import com.assesment.loan.domain.Loan;
import com.assesment.loan.domain.LoanStatus;
import com.assesment.loan.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoanService  {

    @Autowired
    private LoanRepository repository;

    public LoanService() {
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