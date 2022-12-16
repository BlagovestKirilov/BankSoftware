package com.example.bank.service;

import com.example.bank.models.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void importTransaction(Long senderID,Long recipientID,BigDecimal transferAmount) throws Exception;
    void addMoneyToAccount(Long accountId,BigDecimal amount) throws Exception;
    List<Transaction> findAllTransactionsWithTransferAmountGreaterThan(BigDecimal amount);
}
