package com.sample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Model.Customer;
import com.sample.Model.Loan;
import com.sample.Model.Payment;
import com.sample.Services.iCustomerService;
import com.sample.Services.iPaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

	 iPaymentService paymentService;
	
	public PaymentController(iPaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	@PostMapping("/toPay")
	public ResponseEntity<Payment> toPay(@RequestBody Payment p) {
		return new ResponseEntity<Payment>(paymentService.toPay(p), HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity<List<Payment>> getPayments()
	{
		return new ResponseEntity<List<Payment>>(paymentService.getPayments(), HttpStatus.OK);
	}

}
