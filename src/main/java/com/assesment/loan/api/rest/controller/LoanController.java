package com.assesment.loan.controller;

import com.assesment.loan.domain.Loan;
import com.assesment.loan.service.LoanService;
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
import com.assesment.loan.controller.exception.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    public LoanController() {
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        
        //Validate request params
        if(loan.getLoanAmount() == 0 ){
            throw new LoanValidationException("Loan amount cannot be empyty", HttpStatus.BAD_REQUEST);
        }
        if(loan.getTerm() == 0){
            throw new LoanValidationException("Loan amount cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Loan newLoan = loanService.createLoan(loan);
        return new ResponseEntity<>(createdLoan, HttpStatus.OK);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Oops, that loanId was not found on the system",
            HttpStatus.NOT_FOUND);
        }
    }
}