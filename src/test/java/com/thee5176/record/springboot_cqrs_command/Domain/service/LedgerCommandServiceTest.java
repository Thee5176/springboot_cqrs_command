package com.thee5176.record.springboot_cqrs_command.Domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateLedgerDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateLedgerDTOTest;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.LedgerItemsMapper;
import com.thee5176.record.springboot_cqrs_command.Application.mapper.LedgerMapper;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgerItemsTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Ledgers;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgersTest;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.LedgerItemsRepository;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.LedgerRepository;

class LedgerCommandServiceTest {

    private LedgerRepository ledgerRepository;
    private LedgerItemsRepository ledgerItemsRepository;
    private LedgerMapper ledgerMapper;
    private LedgerItemsMapper ledgerItemsMapper;
    private LedgerCommandService recordCommandService;

    @BeforeEach
    void setUp() {
        ledgerRepository = mock(LedgerRepository.class);
        ledgerItemsRepository = mock(LedgerItemsRepository.class);
        ledgerMapper = mock(LedgerMapper.class);
        ledgerItemsMapper = mock(LedgerItemsMapper.class);
        recordCommandService = new LedgerCommandService(
            ledgerRepository, ledgerItemsRepository, ledgerMapper, ledgerItemsMapper
        );
    }


    @Test
    void testCreateRecord_CreatesLedgerAndLedgerItems() {
        CreateLedgerDTO dto = CreateLedgerDTOTest.createSampleCreateLedgerDTO();
        Ledgers ledger = LedgersTest.createSampleLedgers();

        // mock mapping function
        when(ledgerMapper.map(dto)).thenReturn(ledger);
        LedgerItems ledgerItems1 = LedgerItemsTest.createSampleLedgerItems();
        LedgerItems ledgerItems2 = LedgerItemsTest.createSampleLedgerItems();
        when(ledgerItemsMapper.map(dto)).thenReturn(Arrays.asList(ledgerItems1, ledgerItems2));

        // mock repository methods
        doNothing().when(ledgerRepository).createLedger(any(Ledgers.class));
        doNothing().when(ledgerItemsRepository).createLedgerItems(any(LedgerItems.class));

        recordCommandService.createRecord(dto);

        verify(ledgerMapper).map(dto);
        verify(ledgerRepository).createLedger(any(Ledgers.class));
        verify(ledgerItemsMapper).map(dto);
        verify(ledgerItemsRepository, times(2)).createLedgerItems(any(LedgerItems.class));
    }
}