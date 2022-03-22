package com.example.demo.exception;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String message) {
      super(message);		
	}

}
