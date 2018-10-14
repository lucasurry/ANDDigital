package com.andDigital.phoneAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CustomerNotFoundExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String customerNotFoundHandler(CustomerNotFoundException ex) {
		return ex.getMessage();
	}
}

@ControllerAdvice
class PhoneNumberNotFoundExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(PhoneNumberNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String phoneNumberNotFoundHandler(PhoneNumberNotFoundException ex) {
		return ex.getMessage();
	}
}