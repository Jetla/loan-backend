package com.sample.Services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import  org.mockito.ArgumentMatchers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;

import com.sample.Model.Customer;
import com.sample.Model.Loan;
import com.sample.Repository.CustomerRepository;
import com.sample.Repository.LoanRepository;
import com.sample.Service.Impl.LoanServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTests {

	@Mock
	private LoanRepository loanRepo;
	@Mock
	private CustomerRepository customerRepo;
	@InjectMocks
	private LoanServiceImpl loanService;
	
	@Test
	public void testApplyLoanSuccess()
	{
		int customerId=1;
		Customer customer=new Customer();
		customer.setId(customerId);
		
		Loan loan=new Loan();
		loan.setCustomer(customer);
		
		Mockito.when(customerRepo.findById(anyInt())).thenReturn(customer);
		Mockito.when(loanRepo.save(loan)).thenReturn(loan);
		
		Loan result =loanService.applyLoan(loan);
		
		assertNotNull(result);
		assertEquals(loan,result);
		assertEquals(customer,result.getCustomer());
			
	}
//	@Test
//	public void testApplyLoanFailure()
//	{
//		int customerId=1;
//		Customer customer=new Customer();
//		customer.setId(customerId);
//		
//		Loan loan=new Loan();
//		loan.setCustomer(customer);
//		
//		Mockito.when(customerRepo.findById(anyInt())).thenReturn(null);
//		
//		assertThrows(DataIntegrityViolationException.class,()->loanService.applyLoan(loan));
//			
//	}
	@Test
	public void testGetLoansByCustomer()
	{
		int customerId=1;
		Customer customer=new Customer();
		customer.setId(customerId);
		
		List<Loan> loans=new ArrayList<>();
		Loan loan1=new Loan();
		Loan loan2=new Loan();
		loans.add(loan1);
		loans.add(loan2);
		customer.setLoans(loans);
		
		Mockito.when(customerRepo.findById(anyInt())).thenReturn(customer);
		
		List<Loan> result=loanService.getLoansByCustomerId(customerId);
		
		assertNotNull(result);
		assertEquals(loans.size(),result.size());
		assertEquals(loans.get(0),result.get(0));
		assertEquals(loans.get(1),result.get(1));
		}
//	@Test
//	public void testGetLoansByCustomerFailure()
//	{
//		int customerId=1;
//		Mockito.when(customerRepo.findById(anyInt())).thenReturn(null);
//		
//		assertEquals(new ArrayList<Loan>(),loanService.getLoansByCustomerId(customerId));
//	}
	@Test
	public void testModifyLoan()
	{
		Customer customer=new Customer();
		customer.setId(1);
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
	    
		Loan loan=new Loan();
		loan.setLoanId(1);
		loan.setLoanAmt(1000);
		loan.setLoanType("vehicle Loan");
		loan.setDuration(123);
		loan.setMonthlyEMI(1230);
		loan.setCustomer(customer);
		
		//Mockito.when(loanRepo.findById(1)).thenReturn(loan);
		
		Loan modifiedLoan=new Loan();
		modifiedLoan.setLoanId(1);
		modifiedLoan.setLoanAmt(100000);
		modifiedLoan.setLoanType("House Loan");
		modifiedLoan.setDuration(132);
		modifiedLoan.setMonthlyEMI(1234);
		modifiedLoan.setCustomer(customer);
		
		when(loanRepo.findById(1)).thenReturn(loan);
		when(loanRepo.save(any(Loan.class))).thenReturn(modifiedLoan);
		Loan result=loanService.modifyLoans(modifiedLoan);
		verify(loanRepo).findById(1);
		verify(loanRepo).save(loan);
		
		//BeanUtils.copyProperties(modifiedLoan, loan);
		
		//Mockito.when(loanRepo.save(loan)).thenReturn(loan);
	
	    //assertEquals(modifiedLoan,loanService.modifyLoans(modifiedLoan));
		//verify(loanRepo,times(1)).findById(1);
		//verify(loanRepo,times(1)).save(loan);
		assertEquals("House Loan",result.getLoanType());
		assertEquals(100000,result.getLoanAmt(),0.01);
		}
	@Test
	public void testForCloseLoan()
	{
		Loan loan=new Loan();
		loan.setLoanId(1);
		Customer customer=new Customer();
		customer.setId(1);
		loan.setCustomer(customer);
		
		Mockito.when(loanRepo.findById(1)).thenReturn(loan);
		
		loanService.foreCloseLoan(1);
		
		Mockito.verify(loanRepo,Mockito.times(1)).delete(loan);
	}
	}
