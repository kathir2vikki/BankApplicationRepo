package com.app.bank.mgmt.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1l;
	public RecordNotFoundException(String message)
	
	{
		super(message);
	}
}
