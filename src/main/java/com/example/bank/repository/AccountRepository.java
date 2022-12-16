package com.example.bank.repository;

import com.example.bank.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findById(Long id);
    List<Account> findAllByBalanceGreaterThanOrderByBalanceDesc(BigDecimal amount);
}
