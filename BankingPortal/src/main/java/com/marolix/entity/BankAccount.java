package com.marolix.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CustomerDetails")
public class BankAccount 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer accNumber;
private String holderName;
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
	return "BankAccount [accNumber=" + accNumber + ", holderName=" + holderName + ", mobileNumber=" + mobileNumber
			+ ", balance=" + balance + ", accType=" + accType + "]";
}
public BankAccount() {
	super();
	// TODO Auto-generated constructor stub
}


}
