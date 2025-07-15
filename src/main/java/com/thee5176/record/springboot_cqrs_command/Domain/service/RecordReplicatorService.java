package com.thee5176.record.springboot_cqrs_command.Domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.EntryMapper;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.TransactionMapper;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
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
        Transactions transactions = transactionMapper.map(createRecordDTO);
        transactionRepository.createTransaction(transactions);

        //Create Entries
        List<Entries> listOfEntry = entryMapper.map(createRecordDTO);
        listOfEntry.forEach(entryRepository::createEntry);
        //TODO: Does replication service need validation? -> Validator Class
    }
}