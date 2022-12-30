package com.example.bank.service;

import com.example.bank.models.entities.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    Account importAccount(String firstName,String lastName,String iban);
    List<Account> findAllByBalanceGreaterThanOrderByBalanceDesc(BigDecimal amount) throws SQLException;
}
