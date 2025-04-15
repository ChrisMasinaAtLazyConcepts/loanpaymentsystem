package com.assesment.loanpayment.LoanPaymentApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class LoanPaymentApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
    private LoanService loanService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void testLoanCreation() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        Loan savedLoan = loanService.createLoan(loan);
        assertNotNull(savedLoan);
        assertEquals(BigDecimal.valueOf(1000), savedLoan.getLoanAmount());
        assertEquals(LoanStatus.ACTIVE, savedLoan.getLoanStatus());
    }

    @Test
    public void testPaymentCreate() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        Loan savedLoan = loanService.createLoan(loan);

        Payment payment = new Payment();
        payment.setPaymentAmount(BigDecimal.valueOf(500));
        payment.setLoanId(savedLoan.getLoanId());
        Payment savedPayment = paymentService.createPayment(payment);
        assertNotNull(savedPayment);

        Loan updatedLoan = loanRepository.findById(savedLoan.getLoanId()).orElse(null);
        assertNotNull(updatedLoan);
        assertEquals(BigDecimal.valueOf(500), updatedLoan.getLoanAmount());
    }

    @Test
    public void testOverpayment() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        Loan savedLoan = loanService.createLoan(loan);

        Payment payment = new Payment();
        payment.setPaymentAmount(BigDecimal.valueOf(1500));
        payment.setLoanId(savedLoan.getLoanId());

        assertThrows(RuntimeException.class, () -> paymentService.createPayment(payment));
    }

    @Test
    public void testLoanSettlement() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setLoanStatus(LoanStatus.ACTIVE);
        Loan savedLoan = loanService.createLoan(loan);

        Payment payment = new Payment();
        payment.setPaymentAmount(BigDecimal.valueOf(1000));
        payment.setLoanId(savedLoan.getLoanId());
        paymentService.createPayment(payment);

        Loan updatedLoan = loanRepository.findById(savedLoan.getLoanId()).orElse(null);
        assertNotNull(updatedLoan);
        assertEquals(BigDecimal.ZERO, updatedLoan.getLoanAmount());
        assertEquals(LoanStatus.SETTLED, updatedLoan.getLoanStatus());
    }

}
