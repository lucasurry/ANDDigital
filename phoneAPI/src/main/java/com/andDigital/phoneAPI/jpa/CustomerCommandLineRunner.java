package com.andDigital.phoneAPI.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.andDigital.phoneAPI.model.Customer;

@Component
public class CustomerCommandLineRunner implements CommandLineRunner {
	
	final Logger logger = LoggerFactory.getLogger(CustomerCommandLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception{
		
		// Log any pre loaded data
		for(Customer customer : this.customerRepository.findAll()) {
			logger.info("Preloaded data " + customer.toString());
		}

	}
	@Autowired CustomerRepository customerRepository;
}
