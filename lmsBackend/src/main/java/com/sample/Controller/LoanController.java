package com.sample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Model.Loan;
import com.sample.Services.iLoanService;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {

	@Autowired(required = true)
	private iLoanService loanService;

	@PostMapping("/applyLoan")
	public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
		System.out.println(loan);
		return new ResponseEntity<Loan>(loanService.applyLoan(loan), HttpStatus.OK);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable int id) {
		return new ResponseEntity<List<Loan>>(loanService.getLoansByCustomerId(id), HttpStatus.OK);
	}

	@DeleteMapping("/foreclose/{loanId}")
	public ResponseEntity<String> forecloseLoan(@PathVariable int loanId) {
		loanService.foreCloseLoan(loanId);
		return new ResponseEntity<String>("Loan Foreclosed with Loan Id: " + loanId, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Loan>> getLoans()
	{
		return new ResponseEntity<List<Loan>>(loanService.getLoans(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Loan> modifyLoans(@RequestBody Loan loan)
	{
		return new ResponseEntity<Loan>(loanService.modifyLoans(loan),HttpStatus.OK);
	}
	}
