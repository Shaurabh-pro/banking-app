package com.bankingApp.service;

import java.util.List;

import com.bankingApp.dto.AccountDto;

public interface AccountService  {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Integer id);
	
	AccountDto deposit(Integer id, Double amount);
	
	AccountDto withdrow(Integer id, Double amount);
	
	List<AccountDto> getAllAccount();
	
	void deleteAccount(Integer id);

}
