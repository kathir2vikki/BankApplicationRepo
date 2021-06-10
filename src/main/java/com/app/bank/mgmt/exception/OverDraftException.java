package com.app.bank.mgmt.exception;

public class OverDraftException extends RuntimeException {

	private static final long serialVersionUID = 1l;
    public OverDraftException(String message) {
        super(message);
    }


}

