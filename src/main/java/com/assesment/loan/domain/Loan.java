package com.assesment.loan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import com.assesment.loan.domain.LoanStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the status of a loan in our system.
 */
@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @NotNull
    private BigDecimal loanAmount;

    @NotNull
    private Long term;

    private LoanStatus status;

    private LocalDate createdDate;
    
    private LocalDate updatedDate;

}

