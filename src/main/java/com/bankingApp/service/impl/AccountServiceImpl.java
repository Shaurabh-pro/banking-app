package com.bankingApp.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankingApp.dto.AccountDto;
import com.bankingApp.entity.Account;
import com.bankingApp.mapper.AccountMapper;
import com.bankingApp.repository.AccountRepository;
import com.bankingApp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public AccountDto getAccountById(Integer id) {
		
	Account account = accountRepository
			.findById(id)
			.orElseThrow(() -> new RuntimeException("Account does not exist"));
		     return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Integer id, Double amount) {

		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		Double totalBalance = account.getBalance() + amount;
		account.setBalance(totalBalance);
		Account savedAccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdrow(Integer id, Double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		Double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public List<AccountDto> getAllAccount() {
	List<Account> accounts = accountRepository.findAll();
	return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
	.collect(Collectors.toList());
	}



	@Override
	public void deleteAccount(Integer id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
				accountRepository.deleteById(id);
		
	}
	
	

	
	
	
	



}
