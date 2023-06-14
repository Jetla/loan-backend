package com.sample.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.sample.Exception.CustomerAlreadyRegisteredException;
import com.sample.Exception.CustomerNotFoundException;
import com.sample.Model.Customer;
import com.sample.Repository.CustomerRepository;
import com.sample.Service.Impl.CustomerServiceImpl;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

	@Mock
	private CustomerRepository customerRepo;
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@BeforeEach
	public void setUp()
	{
		//customerRepo= Mockito.mock(CustomerRepository.class);
		//customerService=new CustomerServiceImpl(customerRepo);
	}
	@Test
	public void givenCustomerObject_whenSave_thenReturnCustomerObject()
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
		
	    //BDDMockito.given(customerRepo.checkCustomer(customer.getEmail(),customer.getAdhaar(),customer.getPan(),customer.getPhone())).willReturn(customer);
	    BDDMockito.given(customerRepo.checkCustomer(customer.getEmail(),customer.getAdhaar(),customer.getPan(),customer.getPhone())).willReturn(null);
	    BDDMockito.given(customerRepo.save(customer)).willReturn(customer);
	    
	    
	    Customer savedCustomer=customerService.addCustomer(customer);
	     
	    assertEquals(customer,savedCustomer);
	    //Assertions.assertThat(savedCustomer).isNotNull();
	}
	@Test
	public void givenCustomerObject_whenException_thenReturnCustomerObject()
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
		
	    BDDMockito.given(customerRepo.checkCustomer(customer.getEmail(),customer.getAdhaar(),customer.getPan(),customer.getPhone())).willReturn(customer);
	  // BDDMockito.given(customerRepo.save(customer)).willReturn(customer);
	    
	    
	  org.junit.jupiter.api.Assertions.assertThrows(CustomerAlreadyRegisteredException.class,()->{
		  customerService.addCustomer(customer);
	  
	  });
	     
	    verify(customerRepo,never()).save(any(Customer.class));
	}
	
	@Test
	public void givenCustomerId_whenCustomerById_thenReturnCustomerObject()
	{
		int customerID=1;
		Customer customer=new Customer();
		customer.setId(customerID);
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
		
	    BDDMockito.given(customerRepo.findById(1)).willReturn((customer));
	  // BDDMockito.given(customerRepo.save(customer)).willReturn(customer);
	    
	    Customer savedCustomer=customerService.getCustomerById(customerID);
	    
	     assertEquals(customer,savedCustomer);
	     
	   
	}
	@Test
	public void givenCustomerObject_whenUpdate_thenReturnCustomerObject()
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
		
	    Customer updatecustomer=new Customer();
	    updatecustomer.setId(1);
	    updatecustomer.setFname("chandu");
	    updatecustomer.setLname("kumar");
	    updatecustomer.setGender("male");
	    updatecustomer.setPhone(9550969885L);
	    updatecustomer.setEmail("chandu@gmail.com");
	    updatecustomer.setPassword("123456");
	    updatecustomer.setConfirmPassword("123456");
	    updatecustomer.setSalary(1300000);
	    updatecustomer.setAdhaar(543216789868L);
	    updatecustomer.setPan("RTY1221R");
	    
	    BDDMockito.given(customerRepo.findById(updatecustomer.getId())).willReturn(customer);
	    BDDMockito.given(customerRepo.save(customer)).willReturn(customer);
	    
	    Customer savedCustomer=customerService.updateCustomer(updatecustomer);
	    
	    assertEquals(updatecustomer.getFname(),savedCustomer.getFname());
	    assertEquals(updatecustomer.getEmail(),savedCustomer.getEmail());
	 
	}
	@Test
	public void testGetAllCustomer()
	{
		int pageNumber=0;
		int pageSize=10;
		List<Customer> customers=new ArrayList<>();
		customers.add(new Customer(1,"chandu","kumar","male",9550969885L,"chandu@gmail.com","123456","123456",1300000,543216789868L,"RTY1221R"));
		customers.add(new Customer(2,"venu","local","male",7337495780L,"venu@gmail.com","12345","12345",1200000,123456789868L,"RTY1321Y"));
		customers.add(new Customer(3,"vinodh","Redddy","male",9653209765L,"vinodh@gmail.com","12345678","12345678",1100000,123765489868L,"RTY1331H"));
		customers.add(new Customer(4,"venkey","ravi","male",9705703318L,"venkey@gmail.com","123456789","123456789",1400000,543219876868L,"YTY1441R"));
		
		PageRequest pageable=PageRequest.of(pageNumber, pageSize);
		Page<Customer> customerPage=new PageImpl<>(customers,pageable,customers.size());
		
		Mockito.when(customerRepo.findAll(any(PageRequest.class))).thenReturn(customerPage);
		
		List<Customer> result=customerService.getCustomers(pageNumber, pageSize);
		
		assertEquals(customers.size(),result.size());
		for(int i=0;i<customers.size();i++)
		{
			assertEquals(customers.get(i).getId(),result.get(i).getId());
			assertEquals(customers.get(i).getFname(),result.get(i).getFname());
			assertEquals(customers.get(i).getLname(),result.get(i).getLname());
			assertEquals(customers.get(i).getGender(),result.get(i).getGender());
			assertEquals(customers.get(i).getPhone(),result.get(i).getPhone());
			assertEquals(customers.get(i).getEmail(),result.get(i).getEmail());
			assertEquals(customers.get(i).getPassword(),result.get(i).getPassword());
			assertEquals(customers.get(i).getConfirmPassword(),result.get(i).getConfirmPassword());
			assertEquals(customers.get(i).getSalary(),result.get(i).getSalary());
			assertEquals(customers.get(i).getAdhaar(),result.get(i).getAdhaar());
			assertEquals(customers.get(i).getPan(),result.get(i).getPan());
		}
	}
	@Test
	public void testDoLoginSuccess()
	{
		String email="chandu@gmail.com";
		String password="123456";
		Integer customerId=1;
		
		Mockito.when(customerRepo.findCustomerByEmailAndPassword(anyString(),anyString())).thenReturn(customerId);
		
		Integer result=customerService.doLogin(email, password);
		
		assertNotNull(result);
		assertEquals(customerId,result);
	}
//	@Test
//	public void testDoLoginFailure()
//	{
//		String email="chandu@gmail.com";
//		String password="password";
//		Mockito.when(customerRepo.findCustomerByEmailAndPassword(anyString(), anyString())).thenReturn(null);
//	    
//		assertThrows(CustomerNotFoundException.class,()->customerService.doLogin(email,password));
//	}
}
