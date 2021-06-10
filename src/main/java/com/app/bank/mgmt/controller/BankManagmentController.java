/**
 * 
 */
package com.app.bank.mgmt.controller;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bank.mgmt.model.TransactionDo;
import com.app.bank.mgmt.service.BankManagementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author KAR
 *
 */

@RestController
@Validated
@RequestMapping("/customers")
public class BankManagmentController {

	private static final Logger logger = LogManager.getLogger(BankManagmentController.class);

	@Autowired
	BankManagementService bankManagementService;

	@ApiOperation(value = "Deposit Process")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("deposit/{accountNumber}/{amount}")
	public ResponseEntity<?> depositProcess(@PathVariable("accountNumber") Long accountNumber,
			@PathVariable("amount") double amount) {

		logger.info("Getting into deposit process controller  call");
		double availBalance = bankManagementService.depositProcess(accountNumber, amount);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Available Balance in account after deposit = " + availBalance);
	}

	@ApiOperation(value = "Withdraw process")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("withdraw/{accountNumber}/{amount}")
	public ResponseEntity<?> withdrawProcess(@PathVariable("accountNumber") Long accountNumber,
			@PathVariable("amount") double amount) {
		logger.info("Getting into withdraw process controller call");
		double availBalance = bankManagementService.withdrawProcess(accountNumber, amount);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Available Balance in account after withdraw = " + availBalance);
	}

	@ApiOperation(value = "Check Account Balance")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("balance/{accountNumber}")
	public ResponseEntity<?> accountBalance(@PathVariable("accountNumber") Long accountNumber) {
		logger.info("Check balance controller call");
		double balance = bankManagementService.accountBalance(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body("Available Balance in account  = " + balance);
	}

	@ApiOperation(value = "Transaction History")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("transactions/{accountNumber}")
	public ResponseEntity<?> transactionsHistory(@PathVariable("accountNumber") Long accountNumber) {
		logger.info("Getting into transaction history contoller call");
		List<TransactionDo> transactionDo = bankManagementService.transactionsHistory(accountNumber);
		return ResponseEntity.ok(transactionDo);
	}

}
