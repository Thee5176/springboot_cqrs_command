package com.thee5176.record.springboot_cqrs_command.Domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.EntryMapper;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.TransactionMapper;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.EntryRepository;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class RecordCommandService {
    private final TransactionRepository transactionRepository;

    private final EntryRepository entryRepository;

    private final TransactionMapper transactionMapper;

    private final EntryMapper entryMapper;

    @Transactional
    public void createRecord(CreateRecordDTO createRecordDTO) {
        // Create Transaction
        Transactions transaction = transactionMapper.map(createRecordDTO);
        
        transactionRepository.createTransaction(transaction);
        log.info("Transaction created with ID: {}", transaction.getId());

        // Create Entries stream
        entryMapper.map(createRecordDTO).stream()
            .map(entry -> {
                entry.setTransactionId(createRecordDTO.getId());
                return entry;
            })
            .forEach(entryRepository::createEntry);
    }
}