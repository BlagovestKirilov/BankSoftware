package com.example.bank.repository;

import com.example.bank.models.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
 List<Transaction> findAllByTransferAmountIsGreaterThanOrderByTransactionTime(BigDecimal amount);
}
