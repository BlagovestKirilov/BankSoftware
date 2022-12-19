package com.example.bank.controller;

import com.example.bank.models.dto.AccountDto;
import com.example.bank.models.dto.AddMoneyDto;
import com.example.bank.models.dto.TransactionDto;
import com.example.bank.models.entities.Account;
import com.example.bank.service.AccountService;
import com.example.bank.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Transactional
@RequestMapping("/bank/import")
public class ImportController {

    @Autowired private AccountService accountService;
    @Autowired private TransactionService transactionService;

    @PostMapping("/account")
    public Account save(@RequestBody AccountDto accountDto) {
        String firstName = accountDto.getFirstName();
        String lastName = accountDto.getLastName();
        String iban = accountDto.getIban();
        return accountService.importAccount(firstName,lastName,iban);
    }

    @PostMapping("/transaction")
    public void saveTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        Long senderID = transactionDto.getSenderID();
        Long recipientID = transactionDto.getRecipientID();
        BigDecimal transferAmount= transactionDto.getTransferAmount();
        transactionService.transferAmount(senderID,recipientID,transferAmount) ;
    }
    @PostMapping("/addMoney")
    public void addMoney(@RequestBody AddMoneyDto addMoneyDto) throws Exception {
        Long accountID = addMoneyDto.getAccountID();
        BigDecimal amount = addMoneyDto.getAmount();
        transactionService.addMoneyToAccount(accountID,amount);
    }


}
