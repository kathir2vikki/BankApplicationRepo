package com.app.bank.mgmt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bank.mgmt.entity.CustomerAccount;
import com.app.bank.mgmt.entity.Transaction;
import com.app.bank.mgmt.exception.DepositLowException;
import com.app.bank.mgmt.exception.OverDraftException;
import com.app.bank.mgmt.exception.RecordNotFoundException;
import com.app.bank.mgmt.model.TransactionDo;
import com.app.bank.mgmt.model.TransactionType;
import com.app.bank.mgmt.repository.CustomerAccountRepository;
import com.app.bank.mgmt.service.BankManagementService;

@Service
public class BankManagmentServiceImpl implements BankManagementService {

	private static final double MIN_AMOUNT = 0.01;
	private static final Logger logger = LogManager.getLogger(BankManagmentServiceImpl.class);

	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	public double depositProcess(Long accountNumber, double amount){
		logger.info("Getting into deposit process service  call");
		Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(accountNumber);
		if (!accountOptional.isPresent()) {
			throw new RecordNotFoundException("Record not found for accountNumber  = " + accountNumber);
		}
		if (amount <= MIN_AMOUNT) {
			throw new DepositLowException("Amount shoud be superior to € 0.01");
		}

		CustomerAccount account = accountOptional.get();
		double totalBalance = amount + account.getAvailableBalance();
		account.setAvailableBalance(totalBalance);
		account.setAccountType(account.getAccountType());

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCustomerAccount(account);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		account.getTransaction().add(transaction);
		customerAccountRepository.save(account);
		return totalBalance;
	}

	public double withdrawProcess(Long accountNumber, double amount){
		logger.info("Getting into withdraw process service call");
		Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(accountNumber);
		if (!accountOptional.isPresent()) {
			throw new RecordNotFoundException("Record not found for accountNumber  = " + accountNumber);
		}

		CustomerAccount account = accountOptional.get();
		double totalBalance = account.getAvailableBalance() - amount;

		if (totalBalance < 0) {
			throw new OverDraftException("Exceed over draft limit");
		}

		account.setAvailableBalance(totalBalance);
		account.setAccountType(account.getAccountType());

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCustomerAccount(account);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionType(TransactionType.WITHDRAWAL);
		account.getTransaction().add(transaction);
		customerAccountRepository.save(account);
		return totalBalance;

	}

	public Double accountBalance(Long accountNumber){
		logger.info("Check balance service call");
		Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(accountNumber);
		if (!accountOptional.isPresent()) {
			throw new RecordNotFoundException("Record not found for accountId  = " + accountNumber);
		}

		return accountOptional.get().getAvailableBalance();
	}

	public List<TransactionDo> transactionsHistory(Long accountNumber){
		logger.info("Getting into transaction history service call");
		Optional<CustomerAccount> accountOptional = customerAccountRepository.findById(accountNumber);
		if (!accountOptional.isPresent()) {
			throw new RecordNotFoundException("Record not found for accountId  = " + accountNumber);
		}

		List<Transaction> transactions = accountOptional.get().getTransaction();
		return transactions.stream()
				.map(t -> 
				new TransactionDo(t.getTransactionId(),t.getTransactionType(), t.getAmount(), t.getTransactionDate()))
				.collect(Collectors.toList());

	}

}
