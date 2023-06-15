package com.sample.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.Model.Loan;
import com.sample.Model.Payment;

public interface iPaymentService {

	public Payment toPay(Payment p);

	public List<Payment> getPayments();

//	public List<Payment> getpaymentsByCustomerId(int id);


}
