package com.app.bank.mgmt.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.bank.mgmt.controller.BankManagmentController;


@ControllerAdvice(assignableTypes = {BankManagmentController.class})
public class ExceptionController {
	
	private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
	private String BAD_REQUEST = "BAD_REQUEST";
	
	@ExceptionHandler({RecordNotFoundException.class})
	public ResponseEntity<ErrorClass> recordNotFound(RecordNotFoundException e)
	{
		List<String> errorList = new ArrayList<>();
		errorList.add(e.getLocalizedMessage());
		ErrorClass errorMessage = new ErrorClass(INCORRECT_REQUEST, errorList);
		return new ResponseEntity<ErrorClass>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler({DepositLowException.class})
	public ResponseEntity<ErrorClass> lowDepositAmount(DepositLowException e)
	{
		List<String> errorList = new ArrayList<>();
		errorList.add(e.getLocalizedMessage());
		ErrorClass errorMessage = new ErrorClass(INCORRECT_REQUEST, errorList);
		return new ResponseEntity<ErrorClass>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorClass> checkForValidMethodArg(MethodArgumentNotValidException e)
	{
		List<String> listDesp = new ArrayList<>();
		
		e.getBindingResult().getFieldErrors().forEach(ex -> listDesp.add(ex.getField()+ ":"+ ex.getDefaultMessage()));
		ErrorClass errorClass = new ErrorClass(BAD_REQUEST, listDesp);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorClass);
	}

}
