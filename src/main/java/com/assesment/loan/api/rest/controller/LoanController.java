package com.assesment.loan.api.rest.controller;

import com.assesment.loan.domain.Loan;
import com.assesment.loan.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assesment.loan.api.rest.controller.exception.LoanValidationException;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService=loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        //Validate request params
        if(loan.getLoanAmount() == null ){
            throw new LoanValidationException("Loan amount cannot be empyty", HttpStatus.BAD_REQUEST);
        }
        if(loan.getTerm() == null){
            throw new LoanValidationException("Loan amount cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Loan newLoan = loanService.createLoan(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.OK);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
            HttpStatus.NOT_FOUND);
        }
    }
}