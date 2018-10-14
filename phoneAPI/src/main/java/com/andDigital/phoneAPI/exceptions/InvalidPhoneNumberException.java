package com.andDigital.phoneAPI.exceptions;

/**
 * An exception to catch an error in an entered phone number
 * 
 * @author Lucas Urry
 */
@SuppressWarnings("serial")
public class InvalidPhoneNumberException extends Exception{

	public InvalidPhoneNumberException(String message) {
		super(message);
	}

}
