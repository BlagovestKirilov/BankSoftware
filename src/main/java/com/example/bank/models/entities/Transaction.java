package com.example.bank.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account recipient;
    private BigDecimal transferAmount;
    private LocalDateTime transactionTime;
}
