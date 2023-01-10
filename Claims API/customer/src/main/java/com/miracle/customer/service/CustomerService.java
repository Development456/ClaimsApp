package com.miracle.customer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.miracle.customer.model.Customer;


public interface CustomerService {
	
	public ResponseEntity<List<Customer>> getAllCustomers();
	public ResponseEntity<List<Customer>> getAllCustomerFilter(Customer customer, int page, int size, String sort);
	public ResponseEntity<Customer> createCustomer(Customer customer);
	public String deleteCustomer(Long customerId);
	public ResponseEntity<Customer> updateCustomer(Long CustomerId, Customer customer);
	public Customer getByCustomerId(Long customerId);


}
