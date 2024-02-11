package com.bankingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
    List<Account> findByAccountHolderNameContainingIgnoreCase(String name);


}
