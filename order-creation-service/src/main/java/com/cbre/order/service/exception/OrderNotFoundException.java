package com.cbre.order.service.exception;

public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException(String message) {
		super(message);
	}

}
