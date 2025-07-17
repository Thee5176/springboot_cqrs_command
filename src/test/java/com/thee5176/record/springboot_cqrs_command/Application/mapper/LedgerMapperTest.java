package com.thee5176.record.springboot_cqrs_command.Application.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTOTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Ledgers;

public class LedgerMapperTest {

    @Test
    public void testMap_ShouldMapCreateRecordDTOToLedgers() {
        ModelMapper modelMapper = new ModelMapper();
        LedgerMapper ledgerMapper = new LedgerMapper(modelMapper);

        CreateRecordDTO dto = CreateRecordDTOTest.createSampleCreateRecordDTO();

        Ledgers ledger = ledgerMapper.map(dto);

        assertNotNull(ledger);
        assertEquals(dto.getDate(),ledger.getDate());
        assertEquals(dto.getDescription(), ledger.getDescription());
        assertEquals(dto.getTimestamp(), ledger.getCreatedAt());
        assertEquals(dto.getTimestamp(), ledger.getUpdatedAt());
    }

    @Test
    public void testMap_NullDTO_ShouldReturnNullFields() {
        ModelMapper modelMapper = new ModelMapper();
        LedgerMapper ledgerMapper = new LedgerMapper(modelMapper);

        CreateRecordDTO dto = new CreateRecordDTO();

        Ledgers ledger = ledgerMapper.map(dto);

        assertNotNull(ledger);
        assertNull(ledger.getDescription());
    }

}