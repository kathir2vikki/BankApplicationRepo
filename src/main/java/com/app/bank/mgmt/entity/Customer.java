package com.app.bank.mgmt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Long customerId;
	
	private String firstName;

	private String lastName;

	private String emailId;

	private String mobileNo;

	private String area;

	private String city;

	private String state;

	private String pincode;

	@OneToMany(targetEntity = CustomerAccount.class, mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerAccount> customerAccount;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Customer(Long customerId, String firstName, String lastName, String emailId, String mobileNo,
			String area, String city, String state, String pincode, List<CustomerAccount> customerAccount) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.customerAccount = customerAccount;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public List<CustomerAccount> getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(List<CustomerAccount> customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Override
	public String toString() {
		return "BankCustomer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailID=" + emailId + ", mobileNo=" + mobileNo + ", area=" + area + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + "]";
	}

}
