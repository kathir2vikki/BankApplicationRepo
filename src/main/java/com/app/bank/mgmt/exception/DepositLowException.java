package com.app.bank.mgmt.exception;

public class DepositLowException extends RuntimeException {

	private static final long serialVersionUID = 1l;
    public DepositLowException(String message) {
        super(message);
    }


}
