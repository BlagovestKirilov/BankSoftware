package com.example.bank.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class Account extends BaseEntity {
    private String firstName;
    private String lastName;
    private String iban;
    private BigDecimal balance;
}
