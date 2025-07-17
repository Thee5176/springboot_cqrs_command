package com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import com.thee5176.record.springboot_cqrs_command.Domain.model.enums.BalanceType;

public class LedgerItemsTest {

    // Valid COA values from V2 and V3 migrations
    private static final Integer[] VALID_COA = {1101, 1202, 1303, 1404};

    @Test
    void testDefaultConstructorAndSetters() {
        LedgerItems ledger_items = new LedgerItems();
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = VALID_COA[0]; // Use a valid COA
        Double amount = 200.5;
        BalanceType type = BalanceType.Debit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        ledger_items.setId(id)
            .setLedgerId(transactionId)
            .setCoa(coa)
            .setAmount(amount)
            .setType(type)
            .setCreatedAt(createdAt)
            .setUpdatedAt(updatedAt);

        assertEquals(coa, ledger_items.getCoa());
        // ... rest unchanged
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = VALID_COA[1];
        Double amount = 300.75;
        BalanceType type = BalanceType.Credit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        LedgerItems ledger_items = new LedgerItems(id, transactionId, coa, amount, type, createdAt, updatedAt);

        assertEquals(coa, ledger_items.getCoa());
    }

    public static LedgerItems createSampleTestData() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = VALID_COA[2];
        Double amount = 150.0;
        BalanceType type = BalanceType.Debit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        return new LedgerItems(id, transactionId, coa, amount, type, createdAt, updatedAt);
    }
}
