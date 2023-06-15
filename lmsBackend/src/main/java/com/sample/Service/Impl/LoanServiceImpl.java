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
		double intrestRate=getIntrestRate(loan.getLoanType());
		double emi=calculateEMI(loan.getLoanAmt(), intrestRate, (double)loan.getDuration());
		loan.setInterest(intrestRate);
		loan.setMonthlyEMI(emi);
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
	public double getIntrestRate(String loanType) {
		double intrestRate =0;
		switch (loanType) {
		case "Home Loan":
			intrestRate=6;
			break;
		case "Education Loan":
		intrestRate=7;
		break;
		case "Business Loan":
		intrestRate=8;
		break;
		case "Marriage Loan":
			intrestRate=9;
			break;
		case "Health Loan":
			intrestRate=9;
			break;
		case "Vehicle Loan":
			intrestRate=10;

		default:
			intrestRate=8;
			break;
		
		}
		System.out.println("intrest rate :"+intrestRate);
		return intrestRate;
	}
	public double calculateEMI(double p,double r,double t) {
		
		r = r / (12 * 100); // one month interest
		t = t * 12; // one month period
		double emi = (p * r * (double)Math.pow(1 + r, t))
				/ (double)(Math.pow(1 + r, t) - 1);

		//double emi=((amount*intrestRate*(double)(Math.pow( (1+intrestRate), duration))))/((double)(Math.pow((1+intrestRate),duration)-1));
		System.out.println(" EMI :"+emi);
		
		return emi;
	}

	
}
