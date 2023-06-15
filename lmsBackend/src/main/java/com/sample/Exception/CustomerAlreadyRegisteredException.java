package com.sample.Exception;

public class CustomerAlreadyRegisteredException extends RuntimeException {

	public CustomerAlreadyRegisteredException(String message) {
		super(message);
	}

}
