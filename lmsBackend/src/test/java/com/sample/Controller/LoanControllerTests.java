package com.sample.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sample.Model.Customer;
import com.sample.Model.Loan;
import com.sample.Services.iLoanService;

@ExtendWith(MockitoExtension.class)
public class LoanControllerTests {
	@Mock
	private iLoanService loanService;
	@InjectMocks
	private LoanController loanController;

	@Test
	public void testModifyLoan() {
		// create a loan object to modify
		Customer customer=new Customer();
		customer.setFname("anil");
	    customer.setLname("kumar");
	    customer.setGender("male");
	    customer.setPhone(9705703318L);
	    customer.setEmail("venkey@gmail.com");
	    customer.setPassword("12345");
	    customer.setConfirmPassword("12345");
	    customer.setSalary(1200000);
	    customer.setAdhaar(123456789868L);
	    customer.setPan("RTY1231Y");
		Loan loan = new Loan();
		//loan.setId(1);
		//loan.setCustomerId(1);
		//loan.setAmount(1000);
		//loan.setDuration(12);
		loan.setLoanId(1);
		loan.setLoanAmt(1000);
		loan.setLoanType("vehicle Loan");
		loan.setDuration(123);
		loan.setMonthlyEMI(1230);
		loan.setCustomer(customer);
		
		// mock the loan service to return the modified loan object
		when(loanService.modifyLoans(loan)).thenReturn(loan);
		// call the modifyLoans method on the controller
		ResponseEntity<Loan> response = loanController.modifyLoans(loan);
		// assert that the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the loan object we created
		assertEquals(loan, response.getBody());
		}
	@Test 
	public void testGetLoans() {
		// create a list of loan objects 
		Customer customer=new Customer();
		customer.setFname("anil");
	    customer.setLname("kumar");
	    customer.setGender("male");
	    customer.setPhone(9705703318L);
	    customer.setEmail("venkey@gmail.com");
	    customer.setPassword("12345");
	    customer.setConfirmPassword("12345");
	    customer.setSalary(1200000);
	    customer.setAdhaar(123456789868L);
	    customer.setPan("RTY1231Y");
		List<Loan> loans = new ArrayList<>();
		Loan loan1 = new Loan();
		loan1.setLoanId(1);
		loan1.setLoanAmt(1000);
		loan1.setLoanType("vehicle Loan");
		loan1.setDuration(123);
		loan1.setMonthlyEMI(1230);
		loan1.setCustomer(customer);
		loans.add(loan1);
		Loan loan2 = new Loan();
		loan2.setLoanId(1);
		loan2.setLoanAmt(1000);
		loan2.setLoanType("vehicle Loan");
		loan2.setDuration(123);
		loan2.setMonthlyEMI(1230);
		loan2.setCustomer(customer);
		loans.add(loan2);
		// mock the loan service to return the list of loan objects 
		when(loanService.getLoans()).thenReturn(loans);
		// call the getLoans method on the controller 
		ResponseEntity<List<Loan>> response = loanController.getLoans();
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the list of loan objects we created 
		assertEquals(loans, response.getBody()); 
		}
	@Test 
	public void testForecloseLoan() 
	{
		// mock the loan service to do nothing when foreclosing a loan with ID 1 
		doNothing().when(loanService).foreCloseLoan(1);
		// call the forecloseLoan method on the controller 
		ResponseEntity<String> response = loanController.forecloseLoan(1);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body contains the correct message 
		assertEquals("Loan Foreclosed with Loan Id: 1", response.getBody());
		}
	@Test
	public void testGetLoansByCustomerId() {
		// create a list of loan objects for customer with ID 1 
		Customer customer=new Customer();
		customer.setFname("anil");
	    customer.setLname("kumar");
	    customer.setGender("male");
	    customer.setPhone(9705703318L);
	    customer.setEmail("venkey@gmail.com");
	    customer.setPassword("12345");
	    customer.setConfirmPassword("12345");
	    customer.setSalary(1200000);
	    customer.setAdhaar(123456789868L);
	    customer.setPan("RTY1231Y");
		List<Loan> loans = new ArrayList<>();
		Loan loan1 = new Loan();
		loan1.setLoanId(1);
		loan1.setLoanAmt(1000);
		loan1.setLoanType("vehicle Loan");
		loan1.setDuration(123);
		loan1.setMonthlyEMI(1230);
		loan1.setCustomer(customer);
		loans.add(loan1);
		Loan loan2 = new Loan();
		loan2.setLoanId(1);
		loan2.setLoanAmt(1000);
		loan2.setLoanType("vehicle Loan");
		loan2.setDuration(123);
		loan2.setMonthlyEMI(1230);
		loan2.setCustomer(customer);
		loans.add(loan2);
		// mock the loan service to return the list of loan objects for customer with ID 1 
		when(loanService.getLoansByCustomerId(1)).thenReturn(loans);
		// call the getLoansByCustomerId method on the controller 
		ResponseEntity<List<Loan>> response = loanController.getLoansByCustomerId(1);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the list of loan objects we created 
		assertEquals(loans, response.getBody()); 
		}
	@Test 
	public void testApplyLoan() { 
		// create a loan object
		Customer customer=new Customer();
		customer.setFname("anil");
	    customer.setLname("kumar");
	    customer.setGender("male");
	    customer.setPhone(9705703318L);
	    customer.setEmail("venkey@gmail.com");
	    customer.setPassword("12345");
	    customer.setConfirmPassword("12345");
	    customer.setSalary(1200000);
	    customer.setAdhaar(123456789868L);
	    customer.setPan("RTY1231Y");
		Loan loan = new Loan();

		loan.setLoanId(1);
		loan.setLoanAmt(1000);
		loan.setLoanType("vehicle Loan");
		loan.setDuration(123);
		loan.setMonthlyEMI(1230);
		loan.setCustomer(customer); 
		// mock the loan service to return the loan object with ID 1
		when(loanService.applyLoan(loan)).thenReturn(loan);
		// call the applyLoan method on the controller 
		ResponseEntity<Loan> response = loanController.applyLoan(loan);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the loan object we created 
		assertEquals(loan, response.getBody()); 
		}
	
	}

