package com.marolix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marolix.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{

	BankAccount findByMobileNumber(String mobileNumber);

}
