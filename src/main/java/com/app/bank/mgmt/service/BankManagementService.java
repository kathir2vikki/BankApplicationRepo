package com.app.bank.mgmt.service;

import java.util.List;

import com.app.bank.mgmt.model.TransactionDo;

public interface BankManagementService {
	
	public double depositProcess(Long accountNumber, double amount);
	public double withdrawProcess(Long accountNumber, double amount);
	public Double accountBalance(Long accountNumber);
	public List<TransactionDo> transactionsHistory(Long accountNumber);

}
