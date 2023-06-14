package com.sample.Services;//methods interface-methods connectivity

import java.util.List;

import com.sample.Model.Customer;

public interface iCustomerService {

	public Integer doLogin(String email, String password);

	public Customer addCustomer(Customer c);

	public Customer updateCustomer(Customer c);

	public List<Customer> getCustomers(int pageNumber, int pageSize);

	public Customer getCustomerById(int custId);
}
