package com.sample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Model.Customer;
import com.sample.Repository.EmailService;
import com.sample.Services.iCustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
    private EmailService emailservice;
	
	iCustomerService customerService;
	

	//private Logger logger = Logger.getLogger(getClass());

	// Adding Customer

	public CustomerController(iCustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) {
		String emailId = c.getEmail();
		String msg="Your Sucessfully registerd";
    	String subj="Loan Status";
    	boolean flag= emailservice.sendEmail(msg,subj,emailId,"sivanarayan19@gmail.com");
  
		return new ResponseEntity<Customer>(customerService.addCustomer(c), HttpStatus.OK);
	}

	// Updating Customer

	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
		return new ResponseEntity<Customer>(customerService.updateCustomer(c), HttpStatus.OK);
	}

	// Fetching all Customers

	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam("page") int pageNumber,
			@RequestParam("size") int pageSize) {
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(pageNumber, pageSize), HttpStatus.OK);
	}

	// Customer Login

	@PostMapping("/login")
	public ResponseEntity<Integer> doLogin(@RequestParam String email, @RequestParam String password) {
		return new ResponseEntity<Integer>(customerService.doLogin(email, password), HttpStatus.OK);
	}

	// Fetching Customer By Customer Id

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
		return new ResponseEntity<Customer>(customerService.getCustomerById(id), HttpStatus.OK);
	}
	
	 @GetMapping("/sendEmail/{emailId}")
		public String sendEmail(@PathVariable("emailId") String emailId){
//			int min=100000;
//	    	int max=999999;
//	    	int otp=(int)(Math.random()*(max-min+1)+min);
	    	String msg="Your Loan Aplliction is approved succesfully";
	    	String subj="Loan Status";
	    	boolean flag= emailservice.sendEmail(msg,subj,emailId,"sivanarayan19@gmail.com");
	    	if(flag){
	    		return "Email Sent";
	    	}
	    	else{
	    		return "Opertaion failed";
	    	}
	   
	}
}