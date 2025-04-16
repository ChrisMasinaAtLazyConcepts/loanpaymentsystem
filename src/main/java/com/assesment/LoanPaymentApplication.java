package com.assesment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.assesment"})
@EnableAutoConfiguration
public class LoanPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanPaymentApplication.class, args);
	}

}
