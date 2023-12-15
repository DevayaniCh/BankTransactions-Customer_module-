package com.marolix.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BankAccountDTO {
	
	private Integer accNumber;
	@NotNull(message = "name should not be the null")
	@Pattern(regexp = "[a-zA-Z]+",message = "Name should be in characters")
	private String holderName;
	@Pattern(regexp = "[0-9]{10}",message = "Mobile number is invalid")
	private String mobileNumber;
	private double balance;
	private String accType;
	public Integer getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(Integer accNumber) {
		this.accNumber = accNumber;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	@Override
	public String toString() {
		return "BankAccountDTO [accNumber=" + accNumber + ", holderName=" + holderName + ", mobileNumber="
				+ mobileNumber + ", balance=" + balance + ", accType=" + accType + "]";
	}
	public BankAccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
