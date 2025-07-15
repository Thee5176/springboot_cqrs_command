package com.thee5176.record.springboot_cqrs_command.Application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CreateRecordDTOTest {

    @Test
    public void testCreateRecordDTO_AllFields() {
        final UUID id = UUID.randomUUID();
        final LocalDate date = LocalDate.of(2024, 6, 1);
        final String description = "Test description";
        final List<CreateEntryDTO> entries = CreateEntryDTOTest.createSampleEntriesDTO();
        final LocalDateTime timestamp = LocalDateTime.now();

        CreateRecordDTO dto = new CreateRecordDTO(id, date, description, entries, timestamp);

        assertEquals(id, dto.getId());
        assertEquals(date, dto.getDate());
        assertEquals(description, dto.getDescription());
        assertEquals(entries, dto.getEntries());
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    public void testCreateRecordDTO_NullFields() {
        CreateRecordDTO dto = new CreateRecordDTO(null, null, null, null, null);

        assertNull(dto.getId());
        assertNull(dto.getDate());
        assertNull(dto.getDescription());
        assertNull(dto.getEntries());
        assertNull(dto.getTimestamp());
    }

    public static CreateRecordDTO createSampleRecordDTO() {
        final UUID id = UUID.randomUUID();
        final LocalDate date = LocalDate.of(2024, 6, 1);
        final String description = "Test description";
        final List<CreateEntryDTO> entries = CreateEntryDTOTest.createSampleEntriesDTO();
        final LocalDateTime timestamp = LocalDateTime.now();

        final CreateRecordDTO createRecordDTO = new CreateRecordDTO(id, date, description, entries, timestamp);
        return createRecordDTO;
    }
}