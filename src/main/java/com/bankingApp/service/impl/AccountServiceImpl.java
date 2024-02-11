package com.bankingApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
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



	@Override
	public AccountDto updateAccount(Integer id, AccountDto accountDto) {
	    Account account = accountRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exist"));
	    account.setAccountHolderName(accountDto.getAccountHolderName());
	    Account accountUpdate = accountRepository.save(account);
	    return AccountMapper.mapToAccountDto(accountUpdate);
	}



	@Override
	public List<AccountDto> AccountSortByName() {
		List<Account> accounts = accountRepository.findAll(Sort.by(Sort.Direction.ASC, "accountHolderName"));
		return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
	}
	
	
	@Override
	public List<AccountDto> AccountSearchByAccountHolderName(String name) {
		List<Account> accounts = accountRepository.findByAccountHolderNameContainingIgnoreCase(name);
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
		
	}
	



	


	

	
	

	
	
	
	



}
