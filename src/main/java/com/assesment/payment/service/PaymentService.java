
package com.assesment.payment.service;


import org.springframework.stereotype.Service;
import com.assesment.loan.domain.*;

@Service
public interface PaymentService{
    Payment createPayment(Payment payment);
}