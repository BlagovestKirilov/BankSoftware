package com.example.bank.service.impl;

import com.example.bank.models.entities.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class AccountsServiceImpl implements AccountService {
    @Autowired private AccountRepository accountRepository;

    @Override
    public Account importAccount(String firstName,String lastName,String iban) {
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setIban(iban);
        account.setBalance(BigDecimal.valueOf(0.00));
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> findAllByBalanceGreaterThanOrderByBalanceDesc(BigDecimal amount) {
        return accountRepository.findAllByBalanceGreaterThanOrderByBalanceDesc(amount);
    }
}
