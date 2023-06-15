 package com.sample.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sample.Controller.LoanController;
import com.sample.Model.Customer;
import com.sample.Model.Loan;

@DataJpaTest
public class LoanRepositoryTests {

	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private CustomerRepository customerRepo;
	//@Autowired
	//private LoanController loanController;
	
	@Test
	public void givenLoanObject_whenSave_thenReturnLoanObject()
	{
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
	    
	    Loan loan=new Loan();
		loan.setLoanAmt(120000);
		loan.setLoanType("houseLoan");
		loan.setDuration(121);
		loan.setMonthlyEMI(1200);
		loan.setCustomer(customer);
		Loan savedLoan=loanRepo.save(loan);
	    
		   assertThat(savedLoan).isNotNull();
		    assertThat(savedLoan.getLoanId()).isGreaterThan(0);
			}
	/*@Test
	public void givenLoanObject_whenFindAll_thenLoanList()
	{
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
	    
	    Loan loan=new Loan();
		loan.setLoanAmt(120000);
		loan.setLoanType("houseLoan");
		loan.setDuration(121);
		loan.setMonthlyEMI(1200);
		loan.setCustomer(customer);
		loanRepo.save(loan);
		
		Loan loan1=new Loan();
		loan1.setLoanAmt(130000);
		loan1.setLoanType("shopLoan");
		loan1.setDuration(131);
		loan1.setMonthlyEMI(1300);
		loan1.setCustomer(customer);
		
		loanRepo.save(loan1);
		
		List<Loan> loanList=loanRepo.findAll();
		
		assertThat(loanList).isNotNull();
		assertThat(loanList.size()).isEqualTo(2);
	}*/
	@Test
	public void givenLoanObject_whenFindById_thenLoanObject()
	{
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
	    customerRepo.save(customer);
	    
	    Loan loan=new Loan();
		loan.setLoanAmt(120000);
		loan.setLoanType("houseLoan");
		loan.setDuration(121);
		loan.setMonthlyEMI(1200);
		loan.setCustomer(customer);
		loanRepo.save(loan);
	 
		Loan loanDB=loanRepo.findById(loan.getLoanId());
		
		assertThat(loanDB).isNotNull();
	}
	/*@Test
	public void givenLoanObject_whenFindByCustomerId_thenLoanObject()
	{
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
	    customerRepo.save(customer);
	    
	    Loan loan=new Loan();
		loan.setLoanAmt(120000);
		loan.setLoanType("houseLoan");
		loan.setDuration(121);
		loan.setMonthlyEMI(1200);
		loan.setCustomer(customer);
		loanRepo.save(loan);
		
		Loan loan1=new Loan();
		loan1.setLoanAmt(130000);
		loan1.setLoanType("shopLoan");
		loan1.setDuration(131);
		loan1.setMonthlyEMI(1300);
		loan1.setCustomer(customer);
		
		loanRepo.save(loan1);
	 
		//Customer customerDB=loanRepo.findByCustomerId(loan.getCustomer(customer.getId())).get();
		ResponseEntity<List<Loan>> responseEntity=loanController.getLoansByCustomerId(customer.getId());
		List<Loan> loanList=responseEntity.getBody();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(loanList).isNotNull();
	//	assertThat(customerDB).isNotNull();
		assertThat(loanList.size()).isEqualTo(2);
	}*/
	@Test
	public void givenLoanObject_whenmodifyLoan_thenLoanObject()
	{
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
	    
	    Loan loan=new Loan();
		loan.setLoanAmt(120000);
		loan.setLoanType("houseLoan");
		loan.setDuration(121);
		loan.setMonthlyEMI(1200);
		loan.setCustomer(customer);
		loanRepo.save(loan);
	 
		Loan loanDB=loanRepo.findById(loan.getLoanId());	
		loanDB.setLoanAmt(130000);
		loanDB.setLoanType("houseLoan");
		loanDB.setDuration(111);
		loanDB.setMonthlyEMI(1300);
		loanDB.setCustomer(customer);
		
		Loan Updateloan=loanRepo.save(loanDB);
		assertThat(Updateloan.getLoanAmt()).isEqualTo(130000);
		assertThat(Updateloan.getLoanType()).isEqualTo("houseLoan");
		assertThat(Updateloan.getDuration()).isEqualTo(111);
		assertThat(Updateloan.getMonthlyEMI()).isEqualTo(1300);
	}	
	@Test
	public void givenLoanObject_whenDelete_thenLoanObject()
	{
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
	    
	    Loan loan=new Loan();
		loan.setLoanAmt(120000);
		loan.setLoanType("houseLoan");
		loan.setDuration(121);
		loan.setMonthlyEMI(1200);
		loan.setCustomer(customer);
		loanRepo.save(loan);
		
	loanRepo.delete(loan);
	Optional<Loan> loanOptional=Optional.ofNullable(loanRepo.findById(loan.getLoanId()));
	assertThat(loanOptional).isEmpty();
	}
	}
	
