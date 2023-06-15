package com.sample.Controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.offset;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.Model.Customer;
import com.sample.Repository.CustomerRepository;
import com.sample.Service.Impl.CustomerServiceImpl;
import com.sample.Service.Impl.LoanServiceImpl;
import com.sample.Services.iCustomerService;
import com.sample.Services.iLoanService;

import junit.framework.Assert;

//@WebMvcTest
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {
	
	@Mock
	 private iCustomerService customerService;
     
	@InjectMocks
	private CustomerController customerController;
	
	@Test
     public void testAddCustomer() {
      Customer student = new Customer();
     student.setFname("Arun");
     student.setLname("anil");
     student.setGender("male");
     student.setPhone(9705703318L);
     student.setEmail("venkey@gmail.com");
     student.setPassword("12345");
     student.setConfirmPassword("12345");
     student.setSalary(1200000);
     student.setAdhaar(123456789890L);
     student.setPan("RTY1231Y");

//     when(customerService.addCustomer(student)).thenReturn(student);
//     ResponseEntity<Customer> responce=customerController.addCustomer(student);
//     assertEquals(HttpStatus.OK,responce.getStatusCode());
    // assertEquals(student,responce.getBody());
     
    Mockito.when(customerService.addCustomer(student)).thenReturn(student);
     ResponseEntity<Customer> responce=customerController.addCustomer(student);
    assertEquals(HttpStatus.OK, responce.getStatusCode());
     assertEquals(student, responce.getBody());
     }
 

	@Test
	public void testDoLogin() {
		when(customerService.doLogin("venkey@gmail.com", "venkey@034")).thenReturn(1);
		// call the doLogin method on the controller
		ResponseEntity<Integer> response = customerController.doLogin("venkey@gmail.com", "venkey@034");
		// assert that the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is 1 for successful login
		assertEquals(new Integer(1), response.getBody());
		}
	@Test public void testAddCustomerDetails() {
		// create a new customer 
		Customer student = new Customer();
		//student.setId(1);
        student.setFname("Arun");
        student.setLname("anil");
        student.setGender("male");
        student.setPhone(970570331);
        student.setEmail("venkey@gmail.com");
        student.setPassword("12345");
        student.setConfirmPassword("12345");
        student.setSalary(1200000);
        student.setAdhaar(1234567898);
        student.setPan("RTY1231Y");
		// mock the customer service to return the same customer object
		when(customerService.addCustomer(student)).thenReturn(student);
		// call the addCustomer method on the controller 
		ResponseEntity<Customer> response = customerController.addCustomer(student);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the customer object we created
		assertEquals(student, response.getBody());
		}
	
	@Test public void testUpdateCustomer() {
		// create a new customer 
		Customer student = new Customer();
		student.setFname("Arun");
        student.setLname("anil");
        student.setGender("male");
        student.setPhone(970570331);
        student.setEmail("venkey@gmail.com");
        student.setPassword("12345");
        student.setConfirmPassword("12345");
        student.setSalary(1200000);
        student.setAdhaar(1234567898);
        student.setPan("RTY1231Y");
		// mock the customer service to return the same customer object
		when(customerService.updateCustomer(student)).thenReturn(student);
		// call the updateCustomer method on the controller 
		ResponseEntity<Customer> response = customerController.updateCustomer(student);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the customer object we created 
		assertEquals(student, response.getBody());
		}
	@Test
	public void testGetCustomers() {
		// create a list of customers 
		Customer student = new Customer();
		student.setFname("Arun");
        student.setLname("anil");
        student.setGender("male");
        student.setPhone(970570331);
        student.setEmail("venkey@gmail.com");
        student.setPassword("12345");
        student.setConfirmPassword("12345");
        student.setSalary(1200000);
        student.setAdhaar(1234567898);
        student.setPan("RTY1231Y");
		Customer student2 = new Customer();

		student2.setFname("Arun");
        student2.setLname("anil");
        student2.setGender("male");
        student2.setPhone(970570331);
        student2.setEmail("venkey@gmail.com");
        student2.setPassword("12345");
        student2.setConfirmPassword("12345");
        student2.setSalary(1200000);
        student2.setAdhaar(1234567898);
        student2.setPan("RTY1231Y");
		List<Customer> customerList = Arrays.asList(student, student2);
		// mock the customer service to return the list of customers 
		when(customerService.getCustomers(1, 2)).thenReturn(customerList);
		// call the getCustomers method on the controller 
		ResponseEntity<List<Customer>> response = customerController.getCustomers(1, 2);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the list of customers we created 
		assertEquals(customerList, response.getBody()); 
		}
	@Test 
	public void testGetCustomer() { 
		// create a customer with ID 1 
		Customer student = new Customer();
		student.setFname("Arun");
        student.setLname("anil");
        student.setGender("male");
        student.setPhone(970570331);
        student.setEmail("venkey@gmail.com");
        student.setPassword("12345");
        student.setConfirmPassword("12345");
        student.setSalary(1200000);
        student.setAdhaar(1234567898);
        student.setPan("RTY1231Y");
		// mock the customer service to return the customer with ID 1 
		when(customerService.getCustomerById(1)).thenReturn(student); 
		// call the getCustomer method on the controller 
		ResponseEntity<Customer> response = customerController.getCustomer(1);
		// assert that the response status code is OK 
		assertEquals(HttpStatus.OK, response.getStatusCode());
		// assert that the response body is the same as the customer we created 
		assertEquals(student, response.getBody());
		}
		
	}





