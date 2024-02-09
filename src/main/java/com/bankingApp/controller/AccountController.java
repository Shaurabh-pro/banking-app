package com.bankingApp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingApp.dto.AccountDto;
import com.bankingApp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	//add Account REST API
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	//UPDATE ACCOUNT 
	@PutMapping("/kyc/{id}")
	public ResponseEntity<AccountDto> updateAccount(@PathVariable Integer id, @RequestBody AccountDto accountDto){
		AccountDto accountUpdate = accountService.updateAccount(id, accountDto);
		return ResponseEntity.ok(accountUpdate);
	}
	
	// GET ACCOUNT BY ID
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Integer id) {
		AccountDto accountDto = accountService.getAccountById(id);

		return ResponseEntity.ok(accountDto);
	}
	
	//DEPOSIT REST API
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Integer id, @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);

	}
	
	// WITHDROW REST API
	@PutMapping("/{id}/withdrow")
	public ResponseEntity<AccountDto> withdrow(@PathVariable Integer id, @RequestBody Map<String, Double> request){
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.withdrow(id, amount);
		
		return ResponseEntity.ok(accountDto);

	}
	
	// GET ALL ACCOUNT REST API
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
	 List<AccountDto> accounts = accountService.getAllAccount();
	 
	 return ResponseEntity.ok(accounts);
	}
	
	// DELETE ACCOUNT REST API
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccountById(@PathVariable Integer id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted successfully");
	}
	
	
	
	
	
}
