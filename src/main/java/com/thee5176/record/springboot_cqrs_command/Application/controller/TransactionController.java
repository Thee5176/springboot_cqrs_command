package com.thee5176.record.springboot_cqrs_command.Application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    //Repository確認用Controller
    private final TransactionRepository transactionRepository;

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody Transactions transaction) {
        transactionRepository.createTransaction(transaction);
        
        return ResponseEntity.ok("Transaction created succesfully");
    }

    @PutMapping
    public ResponseEntity<String> updateTransaction(@RequestParam UUID uuid, @RequestBody Transactions transaction) {
        transactionRepository.updateTransaction(uuid, transaction);
        
        return ResponseEntity.ok("Transaction updated succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTransaction(@RequestParam UUID uuid) {
        transactionRepository.deleteTransaction(uuid);

        return ResponseEntity.ok("Transaction deleted succesfully");
    }


    @GetMapping
    public List<Transactions> getTransaction() {
        return transactionRepository.getTransactions();
    }
}
