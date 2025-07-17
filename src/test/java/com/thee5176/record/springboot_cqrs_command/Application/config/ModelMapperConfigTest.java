package com.thee5176.record.springboot_cqrs_command.Application.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateLedgerItemsDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateLedgerItemsDTOTest;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTOTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Ledgers;

@SpringBootTest
class ModelMapperConfigTest {
    @Autowired
    private ModelMapperConfig modelMapperConfig;

    @BeforeEach
    void setUp() {
        modelMapperConfig = new ModelMapperConfig();
    }

    @Test
    void testMappingCreateLedgerItemsDTOToLedgerItemsMapping() {
        final CreateLedgerItemsDTO dto = CreateLedgerItemsDTOTest.createSampleCreateLedgerItemsDTO();
        final ModelMapper modelMapper = modelMapperConfig.modelMapper();

        LedgerItems ledgerItems = modelMapper.map(dto, LedgerItems.class);

        assertEquals(dto.getCoa(), ledgerItems.getCoa());
        assertEquals(dto.getAmount(), ledgerItems.getAmount());
        assertEquals(dto.getType(), ledgerItems.getType());
        assertEquals(null, ledgerItems.getId()); 
        assertEquals(null, ledgerItems.getLedgerId()); 
        assertEquals(null, ledgerItems.getCreatedAt()); 
        assertEquals(null, ledgerItems.getUpdatedAt()); 
    }

    @Test
    void testMappingCreateRecordDTOToLedgersWithoutID() {
        CreateRecordDTO dto = CreateRecordDTOTest.createSampleCreateRecordDTO();
        dto.setId(null);
        final ModelMapper modelMapper = modelMapperConfig.modelMapper();

        Ledgers ledgers = modelMapper.map(dto, Ledgers.class);

        assertEquals(dto.getDate(), ledgers.getDate());
        assertEquals(dto.getDescription(), ledgers.getDescription());
        assertEquals(null, ledgers.getId());
        assertEquals(null, ledgers.getCreatedAt());
        assertEquals(null, ledgers.getUpdatedAt());
    }
}