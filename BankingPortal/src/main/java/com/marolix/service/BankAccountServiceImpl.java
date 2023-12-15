package com.marolix.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.marolix.dto.BankAccountDTO;
import com.marolix.entity.BankAccount;
import com.marolix.exceptions.CustomerExceptions;
import com.marolix.repository.BankAccountRepository;
@Service(value = "bankAccountService")
public class BankAccountServiceImpl implements BankAccountService{
	
    @Autowired
	private BankAccountRepository bankRepo;
	@Override
	public String addCustomer(BankAccountDTO dto) throws CustomerExceptions{
		 BankAccount existingCustomer = bankRepo.findByMobileNumber(dto.getMobileNumber());
		    if (existingCustomer != null) 
		        throw new CustomerExceptions("Customer already exists with mobile number: " + dto.getMobileNumber());
		BankAccount bank=new BankAccount();
		//bank.setAccNumber(dto.getAccNumber());
		bank.setAccType(dto.getAccType());
		bank.setBalance(dto.getBalance());
		bank.setHolderName(dto.getHolderName());
		bank.setMobileNumber(dto.getMobileNumber());
	     BankAccount savedAccount = bankRepo.save(bank);
	        return "Customer added sucesfully with account Number"+savedAccount.getAccNumber();
	
	}
	@Override
	public BankAccountDTO viewCustomerDetails(Integer accNumber) throws CustomerExceptions{
		Optional<BankAccount> opt = bankRepo.findById(accNumber);
		BankAccount bank = opt.orElseThrow(()->new CustomerExceptions("no customer found with "+accNumber+" account number"));
		BankAccountDTO dto = new BankAccountDTO();
		dto.setAccType(bank.getAccType());
		dto.setAccNumber(bank.getAccNumber());
		dto.setBalance(bank.getBalance());
		dto.setHolderName(bank.getHolderName());
        dto.setMobileNumber(bank.getMobileNumber());	
        return dto;
	}
	@Override
	public ResponseEntity<String> depositAmount(Integer accNumber, double depositAmount) throws CustomerExceptions {
		 Optional<BankAccount> opt = bankRepo.findById(accNumber);
		 if (opt.isPresent()) {
		        BankAccount bankAccount = opt.get();
		        double currentBalance = bankAccount.getBalance();
		        double updatedBalance = currentBalance + depositAmount;

		        bankAccount.setBalance(updatedBalance);
		        BankAccount savedAccount = bankRepo.save(bankAccount);
		        return ResponseEntity.ok("ruppes "+depositAmount+" sucesfully deposited "+savedAccount);
	        } else {
	            throw new CustomerExceptions("Account number not found");
	        }
	}
	@Override
	public ResponseEntity<String> withdrawAmount(Integer accNumber, double withdrawfund) throws CustomerExceptions {
		Optional<BankAccount> opt = bankRepo.findById(accNumber);

        if (opt.isPresent()) {
            BankAccount bankAccount = opt.get();
            double currentBalance = bankAccount.getBalance();

            if (currentBalance >= withdrawfund) {
                double updatedBalance = currentBalance - withdrawfund;
                bankAccount.setBalance(updatedBalance);
                bankRepo.save(bankAccount);
                return ResponseEntity.ok("you have withdrawn "+withdrawfund+"rs, Remaining balance after withdraw : "+updatedBalance+"rs");
            } else {
                throw new CustomerExceptions("Insufficient balance");
            }
        } else {
            throw new CustomerExceptions("Account number not found");
        }
		
	}
	@Override
	public List<BankAccount> findAllAccounts() throws CustomerExceptions 
	{
		Iterable<BankAccount> itr = bankRepo.findAll();
		Iterator<BankAccount> itr1 = itr.iterator();
		List<BankAccount> list = new ArrayList<BankAccount>();
		while (itr1.hasNext()) {
			list.add(itr1.next());
		}
		if (list.isEmpty())
			throw new CustomerExceptions("no customers found to view");
		return list;
	}
	@Override
	public String deleteCustomer(Integer Id) throws CustomerExceptions {
	Optional<BankAccount> opt=	bankRepo.findById(Id);
    BankAccount bank=	opt.orElseThrow(()-> new CustomerExceptions("no customer found with "+Id));
    bankRepo.deleteById(Id);
    return "deleted customer with "+Id;
		
	}
	@Override
	public ResponseEntity<String> transferAmount(Integer fromAccNumber, Integer toAccNumber, double transferFund) throws CustomerExceptions {
	Optional<BankAccount> fromAccountOpt = bankRepo.findById(fromAccNumber);
	Optional<BankAccount> toAccountOpt = bankRepo.findById(toAccNumber);

	if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
	          BankAccount fromAccount = fromAccountOpt.get();
	          BankAccount toAccount = toAccountOpt.get();

	            if (fromAccount.getBalance() >= transferFund) {
	                fromAccount.setBalance(fromAccount.getBalance() - transferFund);
	                toAccount.setBalance(toAccount.getBalance() + transferFund);

	                bankRepo.save(fromAccount);
	                bankRepo.save(toAccount);

	                return ResponseEntity.ok(transferFund + " transferred from account " + fromAccNumber + " to account " + toAccNumber);
	            } else {
	                return ResponseEntity.ok("insufficient fund to transfer "+fromAccount);
	            }
	        } else {
	            return ResponseEntity.ok("accounts didn't found for transaction");
	        }
	    }
	@Override
	public BankAccountDTO updateMobileNumber(Integer accNumber, String mobileNumber) throws CustomerExceptions {
	Optional<BankAccount> opt=	bankRepo.findById(accNumber);
BankAccount bank=	opt.orElseThrow(()-> new CustomerExceptions("No Customer found with "+accNumber));
bank.setMobileNumber(mobileNumber);
bank = bankRepo.save(bank);
BankAccountDTO dto=new BankAccountDTO();
dto.setAccNumber(accNumber);
dto.setAccType(bank.getAccType());
dto.setBalance(bank.getBalance());
dto.setHolderName(bank.getHolderName());
dto.setMobileNumber(bank.getMobileNumber());
		return dto;
	}
		
	}
	
    


