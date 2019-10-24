package com.comakeit.whms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="The Item quantity is insufficient")
public class InSufficientQuantityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InSufficientQuantityException() {
		super();
	}
	
}
