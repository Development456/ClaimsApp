package com.miracle.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miracle.customer.model.Customer;
import com.miracle.customer.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MongoOperations mongoOperations;
	 
	@Override
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> list = customerRepository.findAll();
		return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	//filter for customer
	@Override
	public ResponseEntity<List<Customer>> getAllCustomerFilter(Customer customer, int page, int size, String sort){
		
		Pageable pageable = PageRequest.of(page, size);
		
		Query query = new Query();
		
		query.with(pageable);
		
		List<Criteria> criteria = new ArrayList<>();
		
		if(customer.getName() != null) {
			criteria.add(Criteria.where("name").is(customer.getName()));
		}
		if(customer.getAddressLine1() != null) {
			criteria.add(Criteria.where("address_line_1").is(customer.getAddressLine1()));
		}
		if(customer.getAddressLine2() != null) {
			criteria.add(Criteria.where("address_line_2").is(customer.getAddressLine2()));
		}
		if(customer.getAddressLine3() != null) {
			criteria.add(Criteria.where("address_line_3").is(customer.getAddressLine3()));
		}
		if(customer.getCity() != null) {
			criteria.add(Criteria.where("city").is(customer.getCity()));
		}
		if(customer.getState() != null) {
			criteria.add(Criteria.where("state").is(customer.getState()));
		}
		if(customer.getPostalCode() != null) {
			criteria.add(Criteria.where("postal_code").is(customer.getPostalCode()));
		}
		if(customer.getCountry() != null) {
			criteria.add(Criteria.where("country").is(customer.getCountry()));
		}
		if(customer.getPhone() != null) {
			criteria.add(Criteria.where("phone").is(customer.getPhone()));
		}
		if(customer.getFax() != null) {
			criteria.add(Criteria.where("fax").is(customer.getFax()));
		}
		if(customer.getEmail() != null) {
			criteria.add(Criteria.where("email").is(customer.getEmail()));
		}
		if(customer.getCreatorId() != null) {
			criteria.add(Criteria.where("creator_id").is(customer.getCreatorId()));
		}
		if(customer.getLastUpdatorId() != null) {
			criteria.add(Criteria.where("last_updator_id").is(customer.getLastUpdatorId()));
		}
		if(customer.getCreateDate() != null) {
			criteria.add(Criteria.where("create_date").is(customer.getCreateDate()));
		}
		if(customer.getLastUpdateDate() != null) {
			criteria.add(Criteria.where("last_update_date").is(customer.getLastUpdateDate()));
		}
		
		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		List<Customer> filteredVals = mongoOperations.find(query, Customer.class);
		
		

		
		return new ResponseEntity<List<Customer>>(filteredVals, new HttpHeaders(), HttpStatus.OK);
	}

	
	
	
	@Override
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResponseEntity<Customer> updateCustomer(Long CustomerId, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Customer getByCustomerId(Long customerId) {
		return customerRepository.findByCustomerId(customerId);

	}


}
