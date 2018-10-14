package com.andDigital.phoneAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andDigital.phoneAPI.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
