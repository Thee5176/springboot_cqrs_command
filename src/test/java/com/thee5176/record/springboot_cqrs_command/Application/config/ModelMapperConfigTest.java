package com.thee5176.record.springboot_cqrs_command.Application.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateEntryDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateEntryDTOTest;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTOTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Transactions;

@SpringBootTest
class ModelMapperConfigTest {
    @Autowired
    private ModelMapperConfig modelMapperConfig;

    @BeforeEach
    void setUp() {
        modelMapperConfig = new ModelMapperConfig();
    }

    @Test
    void testMappingCreateEntryDTOToEntriesMapping() {
        final CreateEntryDTO dto = CreateEntryDTOTest.createExampleEntry();
        final ModelMapper modelMapper = modelMapperConfig.modelMapper();

        Entries entries = modelMapper.map(dto, Entries.class);

        assertEquals(dto.getCoa(), entries.getCoa());
        assertEquals(dto.getAmount(), entries.getAmount());
        assertEquals(dto.getType(), entries.getType());
    }

    @Test
    void testMappingCreateRecordDTOToTransactionsMapping() {
        final CreateRecordDTO dto = CreateRecordDTOTest.createSampleRecordDTO();
        final ModelMapper modelMapper = modelMapperConfig.modelMapper();


        Transactions transactions = modelMapper.map(dto, Transactions.class);

        assertEquals(dto.getId(), transactions.getId());
        assertEquals(dto.getDate(), transactions.getDate());
        assertEquals(dto.getDescription(), transactions.getDescription());
    }
}