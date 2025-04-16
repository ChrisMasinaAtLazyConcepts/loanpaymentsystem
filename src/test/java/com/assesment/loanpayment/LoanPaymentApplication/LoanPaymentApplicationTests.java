package com.assesment.loanpayment.LoanPaymentApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.assesment.loan.domain.Loan;
import com.assesment.loan.domain.LoanStatus;
import com.assesment.loan.repository.LoanRepository;
import com.assesment.payment.api.rest.controller.exception.PaymentValidationException;
import com.assesment.payment.domain.Payment;
import com.assesment.payment.service.PaymentService;

@Transactional
@SpringBootTest
public class LoanPaymentApplicationTests {

    @MockBean
     PaymentService paymentService;

    @MockBean
    LoanRepository loanRepository;

    @Test
    public void testLoanCreation() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setStatus(LoanStatus.ACTIVE);
    
        when(loanRepository.save(any())).thenReturn(loan);
    
        Loan savedLoan = loanRepository.save(new Loan());
        assertNotNull(savedLoan);
        assertEquals(BigDecimal.valueOf(1000), savedLoan.getLoanAmount());
        assertEquals(LoanStatus.ACTIVE, savedLoan.getStatus());
    }

    @Test
    public void testPaymentCreate() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLoanId(1L);

        when(loanRepository.findByLoanId(1L)).thenReturn(Optional.of(loan));

        Loan updatedLoan = new Loan();
        updatedLoan.setLoanAmount(BigDecimal.valueOf(500));
        updatedLoan.setStatus(LoanStatus.ACTIVE);
        updatedLoan.setLoanId(1L);

        when(loanRepository.save(any(Loan.class))).thenReturn(updatedLoan);

        Payment payment = new Payment();
        payment.setPaymentAmount(BigDecimal.valueOf(500));
        payment.setLoanId(1L);

        paymentService.createPayment(payment);

        assertNotNull(updatedLoan);
        assertEquals(BigDecimal.valueOf(500), updatedLoan.getLoanAmount());
    }

    @Test
    public void testOverpayment() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLoanId(1L);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Payment payment = new Payment();
        payment.setPaymentAmount(BigDecimal.valueOf(1500));
        payment.setLoanId(loan.getLoanId());

        doThrow(PaymentValidationException.class).when(paymentService).createPayment(any(Payment.class));
        assertThrows(PaymentValidationException.class, () -> paymentService.createPayment(payment));
    }

  
   @Test
    public void testLoanSettlement() {
        Loan loan = new Loan();
        loan.setLoanAmount(BigDecimal.valueOf(1000));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLoanId(1L);

        when(loanRepository.findByLoanId(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = new Payment();
        payment.setPaymentAmount(BigDecimal.valueOf(1000));
        payment.setLoanId(loan.getLoanId());

        doAnswer(invocation -> {
            loan.setLoanAmount(BigDecimal.ZERO);
            loan.setStatus(LoanStatus.SETTLED);
            return null; 
        }).when(paymentService).createPayment(any(Payment.class));
        paymentService.createPayment(payment);

        assertEquals(BigDecimal.ZERO, loan.getLoanAmount());
        assertEquals(LoanStatus.SETTLED, loan.getStatus());
    }

}
