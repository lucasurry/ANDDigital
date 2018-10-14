package com.andDigital.phoneAPI.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.andDigital.phoneAPI.exceptions.InvalidPhoneNumberException;
import com.andDigital.phoneAPI.exceptions.PhoneNumberNotFoundException;

/**
 * An object used to store information about a customer (name and a list of phone numbers)
 * 
 * @author Lucas Urry
 */
@Entity
@Table(name="customer")
public class Customer {


	@Column(name="customer_id", nullable=false)
	private @Id Long customerId;
	@Column(name="customer_name")
	private String customerName;
	
	// A customer can have many numbers
	@OneToMany(fetch=FetchType.EAGER, mappedBy="customer")
	private Set<PhoneNumber> phoneNumbers;
	
	/**
	 * Add a constructor with a name and a list of phone numbers
	 * 
	 * @param name
	 * @param phoneNumbers
	 */
	public Customer(Long customerId, String customerName, Set<PhoneNumber> phoneNumbers) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNumbers = phoneNumbers;
	}
	
	public Customer() {
	}
	
	public Long getId() {
		return customerId;
	}
	
	public void setID(Long customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Set<PhoneNumber> getPhoneNumbers(){
		return phoneNumbers;
	}
	
	/**
	 * Add inactive phone number
	 * 
	 * @param phoneNumber
	 * @throws InvalidPhoneNumberException
	 */
	public void addPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
		phoneNumbers.add(new PhoneNumber(phoneNumber));
	}
	
	/**
	 * Add phone number with an active flag
	 * 
	 * @param phoneNumber
	 * @param isActive
	 * @throws InvalidPhoneNumberException
	 */
	public void addPhoneNumber(String phoneNumber, boolean isActive) throws InvalidPhoneNumberException {
		phoneNumbers.add(new PhoneNumber(phoneNumber, isActive));
	}
	
	/**
	 * Delete phone number by id
	 * 
	 * @param phoneNumberId
	 * @throws PhoneNumberNotFoundException
	 */
	public void deletePhoneNumber(Long phoneNumberId) throws PhoneNumberNotFoundException {
		PhoneNumber toDelete = null;
		
		for(PhoneNumber phoneNumber : phoneNumbers) {
			if(phoneNumber.getPhoneNumberId() == phoneNumberId) {
				toDelete = phoneNumber;
			}
		}
		
		if(toDelete != null) {
			phoneNumbers.remove(toDelete);
		}else {
			throw new PhoneNumberNotFoundException(phoneNumberId);
		}
	}
	
	/**
	 * Delete phone number
	 * 
	 * @param phoneNumberString
	 * @throws PhoneNumberNotFoundException
	 */
	public void deletePhoneNumber(String phoneNumberString) throws PhoneNumberNotFoundException {
		PhoneNumber toDelete = null;
		
		for(PhoneNumber phoneNumber : phoneNumbers) {
			if(phoneNumber.getPhoneNumber() == phoneNumberString) {
				toDelete = phoneNumber;
			}
		}
		
		if(toDelete != null) {
			phoneNumbers.remove(toDelete);
		}else {
			throw new PhoneNumberNotFoundException(phoneNumberString);
		}
	}

	@Override
	public String toString() {
		return String.format(
				"Customer [id=%s, name=%s, phoneNumbers=%s]", customerId,
				customerName, phoneNumbers);
	}
}
