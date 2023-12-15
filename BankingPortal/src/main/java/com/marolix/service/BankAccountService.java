package com.marolix.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.marolix.dto.BankAccountDTO;
import com.marolix.entity.BankAccount;
import com.marolix.exceptions.CustomerExceptions;

public interface BankAccountService 
{
 String addCustomer(BankAccountDTO dto) throws CustomerExceptions;

 BankAccountDTO viewCustomerDetails(Integer accNumber) throws CustomerExceptions;

ResponseEntity<String> depositAmount(Integer accNumber, double depositAmount) throws CustomerExceptions;

ResponseEntity<String> withdrawAmount(Integer accNumber, double withdrawfund) throws CustomerExceptions;

List<BankAccount> findAllAccounts() throws CustomerExceptions;

String deleteCustomer(Integer Id) throws CustomerExceptions;

ResponseEntity<String> transferAmount(Integer fromAccNumber, Integer toAccNumber, double transferFund) throws CustomerExceptions;

BankAccountDTO updateMobileNumber(Integer accNumber,String mobileNumber) throws CustomerExceptions;

}
