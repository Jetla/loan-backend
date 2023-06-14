package com.sample.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sample.Model.Customer;
import com.sample.Model.Loan;

@DataJpaTest
public class CustomerRepositoryTests {

	@Autowired
	private CustomerRepository customerRepo;
	
	private Customer customer;
	
	@BeforeEach
	public void setup()
	{
		customer=new Customer();
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
	    
	}
	@Test
	public void givenCustomerObject_whenSave_thenReturnCustomerObject()
	{
//	Customer customer=new Customer();
//	customer.setFname("anil");
//    customer.setLname("kumar");
//    customer.setGender("male");
//    customer.setPhone(9705703318L);
//    customer.setEmail("venkey@gmail.com");
//    customer.setPassword("12345");
//    customer.setConfirmPassword("12345");
//    customer.setSalary(1200000);
//    customer.setAdhaar(123456789868L);
//    customer.setPan("RTY1231Y");
    
    Customer savedEmployee=customerRepo.save(customer);
    
   assertThat(savedEmployee).isNotNull();
    assertThat(savedEmployee.getId()).isGreaterThan(0);
	}
	
	@Test
	public void givenCustomerObject_whenFindById_thenReturnCustomerObject()
	{
//	Customer customer=new Customer();
//	customer.setFname("anil");
//    customer.setLname("kumar");
//    customer.setGender("male");
//    customer.setPhone(9705703318L);
//    customer.setEmail("venkey@gmail.com");
//    customer.setPassword("12345");
//    customer.setConfirmPassword("12345");
//    customer.setSalary(1200000);
//    customer.setAdhaar(123456789868L);
//    customer.setPan("RTY1231Y");
    
    Customer savedEmployee=customerRepo.save(customer);

	//Class<Customer> loanDB=(Class<Customer>) ((Object) customerRepo.findById(customer.getId())).getClass();
	Customer customerDB=customerRepo.findById(customer.getId());
	assertThat(customerDB).isNotNull();
	}
	
	@Test
	public void givenCustomerObject_whenUpdate_thenReturnCustomerObject()
	{
//	Customer customer=new Customer();
//	customer.setFname("anil");
//    customer.setLname("kumar");
//    customer.setGender("male");
//    customer.setPhone(9705703318L);
//    customer.setEmail("venkey@gmail.com");
//    customer.setPassword("12345");
//    customer.setConfirmPassword("12345");
//    customer.setSalary(1200000);
//    customer.setAdhaar(123456789868L);
//    customer.setPan("RTY1231Y");
    
    Customer savedEmployee=customerRepo.save(customer);
    
    Customer customerDB=customerRepo.findById(customer.getId());	
    customerDB.setFname("chandu");
    customerDB.setLname("kumar");
    customerDB.setGender("male");
    customerDB.setPhone(9550969885L);
    customerDB.setEmail("chandu@gmail.com");
    customerDB.setPassword("123456");
    customerDB.setConfirmPassword("123456");
    customerDB.setSalary(1300000);
    customerDB.setAdhaar(543216789868L);
    customerDB.setPan("RTY1221X");
    customerRepo.save(customerDB);
    assertThat(customerDB.getFname()).isEqualTo("chandu");
	assertThat(customerDB.getLname()).isEqualTo("kumar");
	assertThat(customerDB.getGender()).isEqualTo("male");
	assertThat(customerDB.getPhone()).isEqualTo(9550969885L);
}
	@Test
	public void givenCustomerObject_whenFindByEmailAndPassword_thenReturnCustomerObject()
	{
//	Customer customer=new Customer();
//	customer.setFname("anil");
//    customer.setLname("kumar");
//    customer.setGender("male");
//    customer.setPhone(9705703318L);
//    customer.setEmail("venkey@gmail.com");
//    customer.setPassword("12345");
//    customer.setConfirmPassword("12345");
//    customer.setSalary(1200000);
//    customer.setAdhaar(123456789868L);
//    customer.setPan("RTY1231Y");
    
   customerRepo.save(customer);
  String email="venkey@gmail.com";
  String password="12345";
	//Class<Customer> customerDB=(Class<Customer>) ((Object) customerRepo.findCustomerByEmailAndPassword(customer.getEmail(),customer.getPassword()).getClass());
	Integer savedCustomer=customerRepo.findCustomerByEmailAndPassword(email, password);
	assertThat(savedCustomer).isNotNull();
	}
	@Test
	public void givenCustomerObject_whenCheckCustomer_thenReturnCustomerObject()
	{
//	Customer customer=new Customer();
//	customer.setFname("anil");
//    customer.setLname("kumar");
//    customer.setGender("male");
//    customer.setPhone(9705703318L);
//    customer.setEmail("venkey@gmail.com");
//    customer.setPassword("12345");
//    customer.setConfirmPassword("12345");
//    customer.setSalary(1200000);
//    customer.setAdhaar(123456789868L);
//    customer.setPan("RTY1231Y");
    customerRepo.save(customer);
   String email="venkey@gmail.com";
   long Adhaar=12345678968L;
   String pan="RTY1231Y";
   long phone=9705703318L;
    
  //  Customer savedEmployee=customerRepo.save(customer);
Customer savedCustomer=customerRepo.checkCustomer(email, Adhaar, pan, phone);
	//Class<Customer> customerDB=(Class<Customer>) ((Object) customerRepo.checkCustomer(customer.getEmail(),customer.getAdhaar(),customer.getPan(),customer.getPhone()).getClass());
	
	assertThat(savedCustomer).isNotNull();
	}
}
