package com.thee5176.record.springboot_cqrs_command.Domain.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.EntryMapper;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.TransactionMapper;
// import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.LedgerItems;
// import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.Ledgers;
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
        final UUID transaction_uuid = UUID.randomUUID();

        // 取引作成stream
        Ledgers transaction = transactionMapper.map(createRecordDTO).setId(transaction_uuid);
        
        transactionRepository.createTransaction(transaction);
        log.info("Transaction created", transaction);
        
        // 取引行別作成stream
        List<LedgerItems> entriesList = entryMapper.map(createRecordDTO);
        log.info("List of entry to be created: {}", entriesList);
        entriesList.forEach(entry -> {
            entry.setId(UUID.randomUUID());
            entry.setTransactionId(transaction_uuid);
            log.info("Entry created: {}", entry);
            entryRepository.createEntry(entry);
        });

    }
}