package com.assesment.loan.domain;

@Getter
@Setter
@Entity
public class Loan{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    
}