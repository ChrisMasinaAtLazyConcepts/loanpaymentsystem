package com.assesment.loan.repository;

import com.assesment.loan.domain.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    Loan save(Loan loan);
    
    Optional<Loan> findByLoanId(Long loanId);
}