package com.app.bank.mgmt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name = "customer_account")
public class CustomerAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	private String accountType;
	private double availableBalance;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	
	@OneToMany(mappedBy = "customerAccount", cascade = CascadeType.ALL)
	private List<Transaction> transaction;

	public CustomerAccount() {
		super();
	}

	public CustomerAccount(String accountType, double availableBalance, 
			List<Transaction> transaction) {
		super();
		this.accountType = accountType;
		this.availableBalance = availableBalance;
	this.customer = customer;
		this.transaction = transaction;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "CustomerAccount [accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", availableBalance=" + availableBalance + ", transaction=" + transaction
				+ "]";
	}

}
