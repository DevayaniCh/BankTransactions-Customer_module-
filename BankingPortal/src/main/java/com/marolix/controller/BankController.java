package com.marolix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marolix.dto.BankAccountDTO;
import com.marolix.entity.BankAccount;
import com.marolix.exceptions.CustomerExceptions;
import com.marolix.service.BankAccountService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "base-url")
@Validated
public class BankController {
    @Autowired
	private BankAccountService bankAccountService;
    
    @PostMapping("/add-customer") 
    public String addCustomerDetails(@Valid @RequestBody BankAccountDTO dto) throws CustomerExceptions {
        String result = bankAccountService.addCustomer(dto);
        return result;
    }
    @GetMapping("/get-customer")
    public BankAccountDTO viewCustomer(@RequestParam(value = "accNumber") Integer accNumber) throws CustomerExceptions {
        return bankAccountService.viewCustomerDetails(accNumber);
    }
    @PutMapping("/deposit/accNumber/{accNumber}/depositFund/{depositFund}")
    public ResponseEntity<String> depositMoney(@PathVariable Integer accNumber, @PathVariable double depositFund) throws CustomerExceptions {
        return bankAccountService.depositAmount(accNumber, depositFund);
    }

    @PutMapping("/withdraw/accNumber/{accNumber}/withdrawFund/{withdrawFund}")
    public ResponseEntity<String> withdrawMoney(@PathVariable Integer accNumber,@PathVariable double withdrawFund) throws CustomerExceptions {
            ResponseEntity<String> response = bankAccountService.withdrawAmount(accNumber, withdrawFund);
           return response; 
}
    @GetMapping(value = "/get-customers")
	public List<BankAccount> viewAllCustomers() throws Exception {
		List<BankAccount> details = bankAccountService.findAllAccounts();
		return details;
	}
    @DeleteMapping(value = "delete-customer")
	public ResponseEntity<String> deleteCustomerDetails(@RequestParam(value = "accNumber") Integer accNumber) throws CustomerExceptions {
		String deleteMessage = bankAccountService.deleteCustomer(accNumber);
		return new ResponseEntity<String>(deleteMessage, HttpStatus.OK);
	}
    @PutMapping("/transfer")
   public  ResponseEntity<String> transferAmount(@RequestParam Integer fromAccount, @RequestParam Integer toAccount , @RequestParam double transferFund) throws CustomerExceptions{
    	return bankAccountService.transferAmount(fromAccount, toAccount, transferFund);
    }
    @PutMapping("update-mobileNumber")
   public BankAccountDTO changeMobileNumber(@RequestParam Integer accNumber, @RequestParam String  mobileNumber) throws CustomerExceptions {
    	return bankAccountService.updateMobileNumber(accNumber, mobileNumber);
    }
}
