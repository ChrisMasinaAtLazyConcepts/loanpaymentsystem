package com.assesment.loan.repository;

import com.assesment.loan.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    Loan createLoan(Loan loan);
    
    Optional<Loan> getLoanById(Long loanId);
}