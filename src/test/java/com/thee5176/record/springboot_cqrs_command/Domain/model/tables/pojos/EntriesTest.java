package com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import com.thee5176.record.springboot_cqrs_command.Domain.model.enums.BalanceType;

public class EntriesTest {

    @Test
    void testDefaultConstructorAndSetters() {
        Entries entries = new Entries();
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = 100;
        Double amount = 200.5;
        BalanceType type = BalanceType.Debit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        entries.setId(id)
            .setTransactionId(transactionId)
            .setCoa(coa)
            .setAmount(amount)
            .setType(type)
            .setCreatedAt(createdAt)
            .setUpdatedAt(updatedAt);

        assertEquals(id, entries.getId());
        assertEquals(transactionId, entries.getTransactionId());
        assertEquals(coa, entries.getCoa());
        assertEquals(amount, entries.getAmount());
        assertEquals(type, entries.getType());
        assertEquals(createdAt, entries.getCreatedAt());
        assertEquals(updatedAt, entries.getUpdatedAt());
    }

    @Test
    void testAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = 101;
        Double amount = 300.75;
        BalanceType type = BalanceType.Credit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Entries entries = new Entries(id, transactionId, coa, amount, type, createdAt, updatedAt);

        assertEquals(id, entries.getId());
        assertEquals(transactionId, entries.getTransactionId());
        assertEquals(coa, entries.getCoa());
        assertEquals(amount, entries.getAmount());
        assertEquals(type, entries.getType());
        assertEquals(createdAt, entries.getCreatedAt());
        assertEquals(updatedAt, entries.getUpdatedAt());
    }

    @Test
    void testCopyConstructor() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = 102;
        Double amount = 400.25;
        BalanceType type = BalanceType.Debit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Entries original = new Entries(id, transactionId, coa, amount, type, createdAt, updatedAt);
        Entries copy = new Entries(original);

        assertEquals(original, copy);
        assertEquals(original.hashCode(), copy.hashCode());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = 103;
        Double amount = 500.0;
        BalanceType type = BalanceType.Credit;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        Entries e1 = new Entries(id, transactionId, coa, amount, type, createdAt, updatedAt);
        Entries e2 = new Entries(id, transactionId, coa, amount, type, createdAt, updatedAt);

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());

        Entries e3 = new Entries();
        assertNotEquals(e1, e3);
        assertNotEquals(e1.hashCode(), e3.hashCode());
    }

    @Test
    void testToString() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = 104;
        Double amount = 600.0;
        BalanceType type = BalanceType.Debit;
        LocalDateTime createdAt = LocalDateTime.of(2024, 1, 1, 12, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2024, 1, 2, 12, 0);

        Entries entries = new Entries(id, transactionId, coa, amount, type, createdAt, updatedAt);
        String str = entries.toString();

        assertTrue(str.contains(id.toString()));
        assertTrue(str.contains(transactionId.toString()));
        assertTrue(str.contains(coa.toString()));
        assertTrue(str.contains(amount.toString()));
        assertTrue(str.contains(type.toString()));
        assertTrue(str.contains(createdAt.toString()));
        assertTrue(str.contains(updatedAt.toString()));
    }

    public static Entries createSampleTestData() {
        UUID id = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();
        Integer coa = 105;
        Double amount = 700.0;
        BalanceType type = BalanceType.Credit;
        LocalDateTime createdAt = LocalDateTime.of(2024, 2, 1, 10, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2024, 2, 2, 10, 0);

        return new Entries(id, transactionId, coa, amount, type, createdAt, updatedAt);
    }
}