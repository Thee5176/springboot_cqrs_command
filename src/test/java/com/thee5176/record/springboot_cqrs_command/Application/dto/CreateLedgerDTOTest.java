package com.thee5176.record.springboot_cqrs_command.Application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CreateLedgerDTOTest {

    @Test
    void testCreateLedgerDTO_AllFields() {
        final UUID id = UUID.randomUUID();
        final LocalDate date = LocalDate.of(2024, 6, 1);
        final String description = "Test description";
        final List<CreateLedgerItemsDTO> ledgerItems = CreateLedgerItemsDTOTest.createPairSampleCreateLedgerItemsDTO();
        final LocalDateTime timestamp = LocalDateTime.now();

        CreateLedgerDTO dto = new CreateLedgerDTO(id, date, description, ledgerItems, timestamp);

        assertEquals(id, dto.getId());
        assertEquals(date, dto.getDate());
        assertEquals(description, dto.getDescription());
        assertEquals(ledgerItems, dto.getLedgerItems());
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void testCreateLedgerDTO_NullFields() {
        CreateLedgerDTO dto = new CreateLedgerDTO(null, null, null, null, null);

        assertNull(dto.getId());
        assertNull(dto.getDate());
        assertNull(dto.getDescription());
        assertNull(dto.getLedgerItems());
        assertNull(dto.getTimestamp());
    }

    public static CreateLedgerDTO createSampleCreateLedgerDTO() {
        final UUID id = UUID.randomUUID();
        final LocalDate date = LocalDate.of(2024, 6, 1);
        final String description = "Test description";
        final List<CreateLedgerItemsDTO> ledgerItems = CreateLedgerItemsDTOTest.createPairSampleCreateLedgerItemsDTO();
        final LocalDateTime timestamp = LocalDateTime.now();

        final CreateLedgerDTO createLedgerDTO = new CreateLedgerDTO(id, date, description, ledgerItems, timestamp);
        return createLedgerDTO;
    }
}