package com.sample.Service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sample.Model.Customer;
import com.sample.Model.Loan;
import com.sample.Repository.CustomerRepository;
import com.sample.Repository.LoanRepository;
import com.sample.Services.iLoanService;

import jakarta.transaction.Transactional;


@Service
@Primary
public class LoanServiceImpl implements iLoanService {

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private CustomerRepository customerDao;

	//private Logger logger = Logger.getLogger(getClass());
   // @Override
	//public Loan applyLoan(Loan loan) {
	//	int customerId = loan.getCustomer().getId();
	//	Customer customer = customerDao.findById(customerId);
				//.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
	//	customer.addLoan(loan);
	//	return loanDao.save(loan);
	//}
	@Override
	@Transactional
	public Loan applyLoan(Loan loan) {
		Customer customer = loan.getCustomer();
		if (customer == null) {
			throw new IllegalArgumentException("Loan must have a customer");
		}
		int customerId = customer.getId();
		customer = customerDao.findById(customerId);
				//.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
		customer.addLoan(loan);
		return loanDao.save(loan);
	}

	@Override
	public List<Loan> getLoansByCustomerId(int customerId) {
		Customer customer = customerDao.findById(customerId);
				//.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
		return customer.getLoans();
		//return this.loanDao.findByCustomerId(customerId);
	}

	@Override
	public void foreCloseLoan(int loanId) {
		Loan loan = loanDao.findById(loanId);//.orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
		loanDao.delete(loan);
	}

	@Override
	public List<Loan> getLoans() {
		return this.loanDao.findAll();
	}

	@Override
	public Loan modifyLoans(Loan l) {
		Loan loan = loanDao.findById(l.getLoanId());
		//	.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
	BeanUtils.copyProperties(l, loan);
	return loanDao.save(loan);
	
	}

	
}
