package com.thee5176.record.springboot_cqrs_command.Domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTOTest;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.EntryMapper;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.TransactionMapper;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.EntriesTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.TransactionsTest;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.EntryRepository;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.TransactionRepository;

class RecordCommandServiceTest {

    private TransactionRepository transactionRepository;
    private EntryRepository entryRepository;
    private TransactionMapper transactionMapper;
    private EntryMapper entryMapper;
    private RecordCommandService recordCommandService;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        entryRepository = mock(EntryRepository.class);
        transactionMapper = mock(TransactionMapper.class);
        entryMapper = mock(EntryMapper.class);
        recordCommandService = new RecordCommandService(
            transactionRepository, entryRepository, transactionMapper, entryMapper
        );
    }

    @Test
    void testCreateRecord_CreatesTransactionAndEntries() {
        CreateRecordDTO dto = CreateRecordDTOTest.createSampleCreateRecordDTO();

        Transactions transaction = TransactionsTest.createSampleTransactions();
        // mock mapping function
        when(transactionMapper.map(dto)).thenReturn(transaction);

        Entries entry1 = EntriesTest.createSampleTestData();
        Entries entry2 = EntriesTest.createSampleTestData();
        when(entryMapper.map(dto)).thenReturn(Arrays.asList(entry1, entry2));

        recordCommandService.createRecord(dto);

        verify(transactionMapper).map(dto);
        verify(transactionRepository).createTransaction(any(Transactions.class));
        verify(entryMapper).map(dto);
        verify(entryRepository, times(2)).createEntry(any(Entries.class));
    }

    // Test Case:
    // - Integration Testing Service-EntryMapper
    // - 
    // - 
    // - 
}