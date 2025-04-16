package com.assesment.loan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.math.BigDecimal;


@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private BigDecimal loanAmount;

    private Long term;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public Long getLoanId() {
        return loanId;
    }
    
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
    
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }
    
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    public Long getTerm() {
        return term;
    }
    
    public void setTerm(Long term) {
        this.term = term;
    }
    
    public LoanStatus getStatus() {
        return status;
    }
    
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

}

