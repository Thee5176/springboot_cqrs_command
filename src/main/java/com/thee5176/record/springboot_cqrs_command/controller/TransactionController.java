package com.thee5176.record.springboot_cqrs_command.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thee5176.record.springboot_cqrs_command.model.tables.pojos.Transactions;
import com.thee5176.record.springboot_cqrs_command.repository.TransactionRepository;

;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping
    public List<Transactions> getTransaction() {
        return transactionRepository.getTransactions();
    }

    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestBody Transactions transaction) {
        transactionRepository.createTransaction(transaction);
        
        return ResponseEntity.ok("Transaction created succesfully");
    }

    @PutMapping
    public ResponseEntity<String> putMethodName(@RequestParam UUID uuid, @RequestBody Transactions transaction) {
        transactionRepository.updateTransaction(uuid, transaction);
        
        return ResponseEntity.ok("Transaction updated succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTransaction(@RequestParam UUID uuid) {
        transactionRepository.deleteTransaction(uuid);

        return ResponseEntity.ok("Transaction deleted succesfully");
    }
}
