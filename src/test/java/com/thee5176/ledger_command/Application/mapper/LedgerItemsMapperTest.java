package com.thee5176.ledger_command.Application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.thee5176.ledger_command.Application.dto.CreateLedgerDTO;
import com.thee5176.ledger_command.Application.dto.CreateLedgerDTOTest;
import com.thee5176.ledger_command.Application.dto.CreateLedgerItemsDTO;
import com.thee5176.ledger_command.Domain.model.tables.pojos.LedgerItems;

class LedgerItemsMapperTest {

    private LedgerItemsMapper LedgerItemsMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        LedgerItemsMapper = new LedgerItemsMapper(modelMapper);
    }

    @Test
    void testMapWithMultipleLedgerItems() {
        CreateLedgerDTO recordDTO = CreateLedgerDTOTest.createSampleCreateLedgerDTO();

        List<LedgerItems> ledgerItemsList = LedgerItemsMapper.map(recordDTO);

        assertEquals(2, ledgerItemsList.size());

        CreateLedgerItemsDTO dto1 = recordDTO.getLedgerItems().get(0);
        CreateLedgerItemsDTO dto2 = recordDTO.getLedgerItems().get(1);
        LedgerItems ledgerItems1 = ledgerItemsList.get(0);
        LedgerItems ledgerItems2 = ledgerItemsList.get(1);

        // Assert for first ledgerItems
        assertEquals(dto1.getCoa(), ledgerItems1.getCoa());
        assertEquals(dto1.getAmount(), ledgerItems1.getAmount());
        assertEquals(dto1.getType(), ledgerItems1.getType());
        assertEquals(recordDTO.getTimestamp(), ledgerItems1.getCreatedAt());
        assertEquals(recordDTO.getTimestamp(), ledgerItems1.getUpdatedAt());

        // Assert for second ledgerItems
        assertEquals(dto2.getCoa(), ledgerItems2.getCoa());
        assertEquals(dto2.getAmount(), ledgerItems2.getAmount());
        assertEquals(dto2.getType(), ledgerItems2.getType());
        assertEquals(recordDTO.getTimestamp(), ledgerItems2.getCreatedAt());
        assertEquals(recordDTO.getTimestamp(), ledgerItems2.getUpdatedAt());
    }

    @Test
    void testMapWithEmptyLedgerItemsList() {
        CreateLedgerDTO recordDTO = new CreateLedgerDTO();
        recordDTO.setLedgerItems(Arrays.asList());
        recordDTO.setTimestamp(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        List<LedgerItems> ledgerItemsList = LedgerItemsMapper.map(recordDTO);

        assertNotNull(ledgerItemsList);
        assertEquals(0, ledgerItemsList.size());
    }
}
