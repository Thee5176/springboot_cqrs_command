package com.thee5176.record.springboot_cqrs_command.Application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateEntryDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTOTest;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;

class EntryMapperTest {

    private EntryMapper entryMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        entryMapper = new EntryMapper(modelMapper);
    }

    @Test
    void testMapWithMultipleEntries() {
        CreateRecordDTO recordDTO = CreateRecordDTOTest.createSampleCreateRecordDTO();

        List<Entries> entriesList = entryMapper.map(recordDTO);

        assertEquals(2, entriesList.size());

        CreateEntryDTO dto1 = recordDTO.getEntries().get(0);
        CreateEntryDTO dto2 = recordDTO.getEntries().get(1);
        Entries entry1 = entriesList.get(0);
        Entries entry2 = entriesList.get(1);

        // Assert for first entry
        assertEquals(dto1.getCoa(), entry1.getCoa());
        assertEquals(dto1.getAmount(), entry1.getAmount());
        assertEquals(dto1.getType(), entry1.getType());
        assertEquals(recordDTO.getTimestamp(), entry1.getCreatedAt());
        assertEquals(recordDTO.getTimestamp(), entry1.getUpdatedAt());

        // Assert for second entry
        assertEquals(dto2.getCoa(), entry2.getCoa());
        assertEquals(dto2.getAmount(), entry2.getAmount());
        assertEquals(dto2.getType(), entry2.getType());
        assertEquals(recordDTO.getTimestamp(), entry2.getCreatedAt());
        assertEquals(recordDTO.getTimestamp(), entry2.getUpdatedAt());
    }

    @Test
    void testMapWithEmptyEntriesList() {
        CreateRecordDTO recordDTO = new CreateRecordDTO();
        recordDTO.setEntries(Arrays.asList());
        recordDTO.setTimestamp(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        List<Entries> entriesList = entryMapper.map(recordDTO);

        assertNotNull(entriesList);
        assertEquals(0, entriesList.size());
    }
}
