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

@Service
@AllArgsConstructor
public class RecordReplicatorService {
    private final TransactionRepository transactionRepository;

    private final EntryRepository entryRepository;

    private final TransactionMapper transactionMapper;

    private final EntryMapper entryMapper;

    @Transactional
    public void replicateRecord(CreateRecordDTO createRecordDTO) {
        //Create Transaction
        Transactions transactions = transactionMapper.map(createRecordDTO.transaction());

        //debug
        System.out.println(transactions);
        
        transactions.setCreatedAt(createRecordDTO.timestamp());
        transactions.setUpdatedAt(createRecordDTO.timestamp());
        transactionRepository.createTransaction(transactions);

        //Create Entries
        createRecordDTO.entries().stream()
            .map(entryMapper::map)
            .map(entry -> entry.setCreatedAt(createRecordDTO.timestamp()))
            .map(entry -> entry.setUpdatedAt(createRecordDTO.timestamp()))
            .forEach(entryRepository::createEntry);

        //TODO: Does replication service need validation? -> Validator Class
    }
}
