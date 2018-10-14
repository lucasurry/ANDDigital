package com.andDigital.phoneAPI.model;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.andDigital.phoneAPI.exceptions.InvalidPhoneNumberException;

/**
 * An object to store a phone number and it's state
 * 
 * @author Lucas Urry
 */
@Entity
@Table(name="phone_number")
public class PhoneNumber {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long phoneNumberId;
	
	@Column(name = "customer_id", nullable=false)
	private Long customerId;
	
	// One customer can have many numbers
	@ManyToOne(optional=false)
	@JoinColumn(name="customer_id",referencedColumnName="customer_id", insertable=false, updatable=false)
	private Customer customer;
	
	// A phone number can contain area codes with a + and spaces so make this a String
	// Also prevents 0 being removed from the front of the phone number!
	@Column(name = "number")
	private String number;
	@Column(name = "is_active")
	private boolean isActive;
	
	public PhoneNumber() {};
	
	/**
	 * Assume that the default numbers being added will be inactive
	 * 
	 * @param number
	 * @throws Exception
	 */ 
	public PhoneNumber(String number) throws InvalidPhoneNumberException {
		super();
		setNumber(number);
		isActive = false;
	}
	
	/**
	 * Give the option to add phone numbers in an active or inactive state
	 * 
	 * @param number
	 * @param isActive
	 * @throws Exception
	 */
	public PhoneNumber(String number, boolean isActive) throws InvalidPhoneNumberException {
		super();
		setNumber(number);
		this.isActive = isActive;
	}
	
	public Long getPhoneNumberId() {
		return phoneNumberId;
	}
	
	public void setPhoneNumberId(Long phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}
	
	public String getPhoneNumber() {
		return number;
	}
	
	/**
	 * As the phone number is a String does some basic checks to make sure there are no
	 * unexpected characters
	 * 
	 * Checks that the length of the phone number is valid
	 * 
	 * @param number
	 * @throws Exception
	 */
	public void setNumber(String number) throws InvalidPhoneNumberException {
		// Remove any blank spaces from the String
		number = number.replaceAll(" ", "");
		
		// Check that there are only numbers in the phone number
		if(!Pattern.matches("[0-9]+", number)) {
			throw new InvalidPhoneNumberException("Found non numerical characters in the phone number");
		}
		
		// Check that the length of the phone number is as expected
		if(number.length() < 10 || number.length() > 10) {
			throw new InvalidPhoneNumberException("Phone number was not 10 digits long");
		}
		
		this.number = number;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void activate() {
		this.isActive = true;
	}
	
	@Override
	public String toString() {
		return String.format(
				"PhoneNumber [id=%s, number=%s, isActive=%s]", phoneNumberId, number, isActive);
	}
	

}
