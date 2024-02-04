package com.bankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
