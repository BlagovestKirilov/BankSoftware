package com.example.bank.controller;

import com.example.bank.models.entities.Account;
import com.example.bank.models.entities.Transaction;
import com.example.bank.service.AccountService;
import com.example.bank.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bank/export")
public class ExportController {
    @Autowired private AccountService accountService;
    @Autowired private TransactionService transactionService;

    @GetMapping("/getAccounts/{amount}")
    public List<Account> findAllAccountsByBalanceGreaterThan(@PathVariable BigDecimal amount) throws SQLException {
        return accountService.findAllByBalanceGreaterThanOrderByBalanceDesc(amount);
    }
    @GetMapping("/getTransactions/{amount}")
    public List<Transaction> findAllTransactionsByTransferAmountGreaterThan(@PathVariable BigDecimal amount){
        return transactionService. findAllTransactionsWithTransferAmountGreaterThan(amount);
    }
}
