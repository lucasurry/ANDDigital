package com.andDigital.phoneAPI.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.andDigital.phoneAPI.exceptions.CustomerNotFoundException;
import com.andDigital.phoneAPI.exceptions.PhoneNumberNotFoundException;
import com.andDigital.phoneAPI.jpa.CustomerRepository;
import com.andDigital.phoneAPI.model.Customer;
import com.andDigital.phoneAPI.model.PhoneNumber;

/**
 * Controller for the REST API
 * 
 * @author Lucas Urry
 *
 */
@RestController
public class CustomerController {
	
	private final CustomerResourceAssembler assembler;
	
	CustomerController(CustomerResourceAssembler assembler) {
		this.assembler = assembler;
	}
	
	/**
	 * Returns a list of all customers with their phone numbers
	 * 
	 * @return A list of customer resources as a JSON String
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<Customer>> getAllCustomers(){
		List<Resource<Customer>> customers = customerRepository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());
		
		return new Resources<>(customers,
				linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
	}
	
	/**
	 * Returns the customer with specified id 
	 * 
	 * @param id
	 * @return The customer with the given id
	 * @throws CustomerNotFoundException
	 */
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Customer> getOneCustomer(@PathVariable Long id) throws CustomerNotFoundException {
		
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		
		return assembler.toResource(customer);
	}

	/**
	 * Activates the phone number for the given phone number id and user id 
	 * 
	 * @param id
	 * @param numberId
	 * @return The updated customer object
	 * @throws CustomerNotFoundException
	 */
	@RequestMapping(value = "/customers/{id}/phone_number/{numberId}/activate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer activatePhoneNumber(@PathVariable Long id, @PathVariable Long numberId) throws CustomerNotFoundException {
		return customerRepository.findById(id)
					.map(customer -> {
						for(PhoneNumber number : customer.getPhoneNumbers()) {
							if(number.getPhoneNumberId() == numberId) {
								number.activate();
								break;
							}
						}
						
						return customerRepository.save(customer);
					}).orElseThrow(() -> new CustomerNotFoundException(id));
	}
	
	@Autowired CustomerRepository customerRepository;
}

/**
 * Build the path to be appended to customer
 * 
 * @author Lucas Urry
 *
 */
@Component
class CustomerResourceAssembler implements ResourceAssembler<Customer, Resource<Customer>>{

	@Override
	public Resource<Customer> toResource(Customer customer) {
		try {
			return new Resource<>(customer,
					linkTo(methodOn(CustomerController.class).getOneCustomer(customer.getId())).withSelfRel(),
					linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
		} catch (CustomerNotFoundException e) {
			return null;
		}
	}
}
