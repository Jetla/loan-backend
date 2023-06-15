package com.sample.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sample.Model.Customer;
import com.sample.Model.Loan;
import com.sample.Model.Payment;
import com.sample.Repository.CustomerRepository;
import com.sample.Repository.PaymentRepository;
import com.sample.Services.iPaymentService;

import jakarta.transaction.Transactional;

@Service
@Primary
public class PaymentServiceImpl implements iPaymentService{

	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private CustomerRepository customerDao;
	@Override
	public Payment toPay(Payment p) {
		
		return paymentRepo.save(p);
	}
//	@Override
//	@Transactional
//	public Payment toPay(Payment payment) {
//		Customer customer = payment.getCustomer();
//		if (customer == null) {
//			throw new IllegalArgumentException("payment must have a customer");
//		}
//		int customerId = customer.getId();
//		customer = customerDao.findById(customerId);
//				//.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
//		customer.toPay(payment);
//		return paymentRepo.save(payment);
//	}

	@Override
	public List<Payment> getPayments() {
		// TODO Auto-generated method stub
		return this.paymentRepo.findAll();
	}

//	@Override
//	public List<Payment> getpaymentsByCustomerId(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
