package com.andDigital.phoneAPI.exceptions;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception{

	public CustomerNotFoundException(String message) {
		super(message);
	}
	
	public CustomerNotFoundException(Long id) {
		super("Could not find customer " + id.toString());
	}
}
