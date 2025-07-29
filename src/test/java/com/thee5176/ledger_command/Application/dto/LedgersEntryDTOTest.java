package com.thee5176.ledger_command.Application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class LedgersEntryDTOTest {
    // Test data for Dr/Cr balaced Ledgers
    // Arrange
    final UUID id = UUID.randomUUID();
    final LocalDate date = LocalDate.of(2024, 6, 1);
    final String description = "Test description";
    final List<LedgerItemsEntryDTO> ledgerItems = LedgerItemsEntryDTOTest.createBalancedSampleLedgerItemsEntryDTO();
    final LocalDateTime timestamp = LocalDateTime.now();

    @Test
    void testLedgersEntryDTO_AllFields() {
        // Act
        LedgersEntryDTO dto = new LedgersEntryDTO(id, date, description, ledgerItems, timestamp);

        // Assertion
        assertEquals(id, dto.getId());
        assertEquals(date, dto.getDate());
        assertEquals(description, dto.getDescription());
        assertEquals(ledgerItems, dto.getLedgerItems());
        assertEquals(timestamp, dto.getTimestamp());
    }

    @Test
    void testLedgersEntryDTO_NullFields() {
        LedgersEntryDTO dto = new LedgersEntryDTO(null, null, null, null, null);

        assertNull(dto.getId());
        assertNull(dto.getDate());
        assertNull(dto.getDescription());
        assertNull(dto.getLedgerItems());
        assertNull(dto.getTimestamp());
    }

    // Reusable Test Data
    public static LedgersEntryDTO createSampleLedgersEntryDTO() {
        final UUID id = UUID.randomUUID();
        final LocalDate date = LocalDate.of(2024, 6, 1);
        final String description = "Test description";
        final List<LedgerItemsEntryDTO> ledgerItems = LedgerItemsEntryDTOTest.createBalancedSampleLedgerItemsEntryDTO();
        final LocalDateTime timestamp = LocalDateTime.now();

        final LedgersEntryDTO ledgersEntryDTO = new LedgersEntryDTO(id, date, description, ledgerItems, timestamp);
        return ledgersEntryDTO;
    }
}
