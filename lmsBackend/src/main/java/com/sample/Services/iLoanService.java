package com.sample.Services;

import java.util.List;

import com.sample.Model.Loan;

public interface iLoanService {

	public Loan applyLoan(Loan l);

	public List<Loan> getLoansByCustomerId(int custId);

	public void foreCloseLoan(int loanId);
	
	public Loan modifyLoans(Loan l);

	public List<Loan> getLoans();

}
