package com.sample.Exception;

public class LoanNotFoundException extends RuntimeException{

	public LoanNotFoundException(String message){
		super(message);
	}
}
