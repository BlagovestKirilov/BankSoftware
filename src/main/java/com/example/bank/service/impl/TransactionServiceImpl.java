package com.example.bank.service.impl;

import com.example.bank.exceptions.AccountsWithSameIds;
import com.example.bank.exceptions.NoSuchAccountException;
import com.example.bank.models.entities.Account;
import com.example.bank.models.entities.Transaction;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void transferAmount(Long senderID, Long recipientID, BigDecimal transferAmount) {
        checkIds(senderID, recipientID);

        Account senderAccount = getAccountById(senderID);
        Account recipientAccount = getAccountById(recipientID);

        checkBalance(senderAccount, transferAmount);

        Transaction transaction = createTransaction(senderAccount, recipientAccount, transferAmount);
        Transaction oppositeTransaction = createTransaction(recipientAccount, senderAccount, transferAmount.negate());

        changeBalance(senderAccount, transferAmount.negate());
        changeBalance(recipientAccount, transferAmount);

        this.transactionRepository.saveAll(List.of(transaction, oppositeTransaction));
    }

    @Override
    public void addMoneyToAccount(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void withdrawMoney(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        checkBalance(account, amount);
        addMoneyToAccount(accountId, amount.negate());
    }

    @Override
    public List<Transaction> findAllTransactionsWithTransferAmountGreaterThan(BigDecimal amount) {
        return transactionRepository.findAllByTransferAmountIsGreaterThanOrderByTransactionTime(amount);
    }

    public Transaction createTransaction(Account senderAccount, Account recipientAccount, BigDecimal transferAmount) {
        Transaction transaction = new Transaction();
        transaction.setSender(senderAccount);
        transaction.setRecipient(recipientAccount);
        transaction.setTransferAmount(transferAmount);
        transaction.setTransactionTime(LocalDateTime.now());
        return transaction;
    }

    public void changeBalance(Account account, BigDecimal transferAmount) {
        account.setBalance(account.getBalance().add(transferAmount));
    }

    public void checkBalance(Account account, BigDecimal transferAmount) {
        if (account.getBalance().subtract(transferAmount).compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new IllegalArgumentException(String.format("The account with ID: %d does not have enough money!", account.getId()));
        }
    }

    public void checkIds(Long senderID, Long recipientID) {
        if (senderID.compareTo(recipientID) == 0) {
            throw new AccountsWithSameIds("Accounts are with same id!");
        }
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchAccountException(String.format("There is no account with ID: %d", id)));
    }

}
