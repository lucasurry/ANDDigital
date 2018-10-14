package com.andDigital.phoneAPI.exceptions;

@SuppressWarnings("serial")
public class PhoneNumberNotFoundException extends Exception{

	public PhoneNumberNotFoundException(String message) {
		super(message);
	}
	
	public PhoneNumberNotFoundException(Long id) {
		super("Could not find phone number " + id.toString());
	}
}