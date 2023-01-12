package com.miracle.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.customer.exception.ErrorDetails;
import com.miracle.customer.model.Customer;
import com.miracle.customer.service.CustomerServiceImpl;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class CustomerController {
	/** The customer services. */
	@Autowired
	private CustomerServiceImpl customerServices;
	
	/**
	 * Gets all customers.
	 *
	 * @returns all customers
	 */
	@Timed(
			value = "customer.getAll",
			histogram = true,
			percentiles = {0.95, 0.99},
			extraTags = {"version", "1.0"}
			)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns All Customer details", notes = "JSON Supported", response = Customer.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Customer.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Customers service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return customerServices.getAllCustomers();
	}
	
	
	//filter
	//http://localhost:8400/filter?page=1&size=10&sort=claimId
	@Timed(
			value = "customer.getAll",
			histogram = true,
			percentiles = {0.95, 0.99},
			extraTags = {"version", "1.0"}
			)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns All Customer, Filtered", notes = "JSON Supported", response = Customer.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Customer.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Facility service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/filter")
	public ResponseEntity<List<Customer>> getFilter(@RequestHeader Map<String, String> headers, @Param(value= "page") int page, @Param(value="size") int size, @Param(value="sort") String sort) {
		Customer customer = new Customer();
		headers.forEach((key,value)->{  
			
			if(key.equalsIgnoreCase("name")) {
				customer.setName(value);
			}
			else if(key.equalsIgnoreCase("addressLine1")) {
				customer.setAddressLine1(value);
			}
			
			else if(key.equalsIgnoreCase("addressLine2")) {
				customer.setAddressLine2(value);
			}
			
			else if(key.equalsIgnoreCase("addressLine3")) {
				customer.setAddressLine3(value);
			}
			
			else if(key.equalsIgnoreCase("city")) {
				customer.setCity(value);
			}
			
			else if(key.equalsIgnoreCase("state")) {
				customer.setState(value);
			}
			
			else if(key.equalsIgnoreCase("postalCode")){
				customer.setPostalCode(value);
			}
			
			else if(key.equalsIgnoreCase("country")) {
				customer.setCountry(value);
			}
			
			else if(key.equalsIgnoreCase("phone")) {
				customer.setPhone(value);
			}
			
			else if(key.equalsIgnoreCase("fax")) {
				customer.setFax(value);
			}
			else if(key.equalsIgnoreCase("email")) {
				customer.setEmail(value);
			}
			else if(key.equalsIgnoreCase("creator_id")) {
				customer.setCreatorId(value);
			}
			else if(key.equalsIgnoreCase("last_updator_id")) {
				customer.setLastUpdatorId(value);
			}
			else if(key.equalsIgnoreCase("create_date")) {
				customer.setCreateDate(value);
			}
			else if(key.equalsIgnoreCase("last_update_date")) {
				customer.setLastUpdateDate(value);
			}
		});
		return customerServices.getAllCustomerFilter(customer, page, size, sort);
	}
	/**
	 * Gets the customer by customer id.
	 *
	 * @param customerId the customer id
	 * @return the customer by customer id
	 */
	@Timed(
			value = "customer.getAll",
			histogram = true,
			percentiles = {0.95, 0.99},
			extraTags = {"version", "1.0"}
			)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get Customer By customer Id", notes = "JSON Supported", response = Customer.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Customer.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Claims service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getByCustomerId(
			@ApiParam(value = "customer Id", required = true) @PathVariable Long customerId) {
		return new ResponseEntity<Customer>(customerServices.getByCustomerId(customerId), new HttpHeaders(),
				HttpStatus.OK);
	}

	/**
	 * Creates the Customers.
	 *
	 * @param Customer the Customer
	 * @return the response entity
	 */
	@Timed(
			value = "customer.getAll",
			histogram = true,
			percentiles = {0.95, 0.99},
			extraTags = {"version", "1.0"}
			)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create Customer", notes = "JSON Supported", response = Customer.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Customer.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Customers service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomers(
			@ApiParam(value = "Customer Request", required = true) @RequestBody Customer customer) {
		return customerServices.createCustomer(customer);
	}

	/**
	 * Update Customer.
	 *
	 * @param CustomerId the Customer id
	 * @param Customer   the Customer
	 * @return the response entity
	 */
	@Timed(
			value = "customer.getAll",
			histogram = true,
			percentiles = {0.95, 0.99},
			extraTags = {"version", "1.0"}
			)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update Customer", notes = "JSON Supported", response = Customer.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Customer.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Customers service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@PutMapping("/Customers/{CustomerId}")
	public ResponseEntity<Customer> updateCustomer(
			@ApiParam(value = "Customer Id", required = true) @PathVariable Long CustomerId,
			@ApiParam(value = "Customer Request", required = true) @RequestBody Customer Customer) {
		return customerServices.updateCustomer(CustomerId, Customer);
	}

	/**
	 * Delete Customers.
	 *
	 * @param CustomerId the Customer id
	 * @return the string
	 */
	@Timed(
			value = "customer.getAll",
			histogram = true,
			percentiles = {0.95, 0.99},
			extraTags = {"version", "1.0"}
			)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete Customer", notes = "JSON Supported", response = Customer.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "success", response = Customer.class),
			@ApiResponse(code = 400, message = "bad-request", response = ErrorDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDetails.class),
			@ApiResponse(code = 403, message = "Customers service requires authentication - please check username and password", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Data not found", response = ErrorDetails.class),
			@ApiResponse(code = 405, message = "Method not allowed", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class) })
	@DeleteMapping("/customer/{customerId}")
	public String deleteCustomers(
			@ApiParam(value = "Customer Id", required = true) @PathVariable Long customerId) {
		return customerServices.deleteCustomer(customerId);
	}
	
}
