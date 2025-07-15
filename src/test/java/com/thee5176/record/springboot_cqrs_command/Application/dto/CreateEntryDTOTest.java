package com.thee5176.record.springboot_cqrs_command.Application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.thee5176.record.springboot_cqrs_command.Domain.model.enums.BalanceType;

public class CreateEntryDTOTest {

    @Test
    public void canCreateDebitEntry() {
        // Test data for a debit entry (e.g., for an asset or expense account)
        // Arrange
        final Integer coa = 1101; // Example Chart of Accounts code for "Cash"
        final Double amount = 250.50;
        final BalanceType type = BalanceType.Debit;

        //Act
        CreateEntryDTO debitEntry = new CreateEntryDTO(coa, amount, type);
        
        //Assert
        assertNotNull(debitEntry);
        assertEquals(coa, debitEntry.coa());
        assertEquals(amount, debitEntry.amount());
        assertEquals(type, debitEntry.type());
    }

    @Test
    public void canCreateCreditEntry() {
        // Test data for a credit entry (e.g., for a liability, equity, or revenue account)
        // Arrange
        final Integer coa = 4101; // Example Chart of Accounts code for "Sales Revenue"
        final Double amount = 250.50;
        final BalanceType type = BalanceType.Credit;

        //Act
        CreateEntryDTO creditEntry = new CreateEntryDTO(coa, amount, type);

        //Assert
        assertNotNull(creditEntry);
        assertEquals(coa, creditEntry.coa());
        assertEquals(amount, creditEntry.amount());
        assertEquals(type, creditEntry.type());
}

    public static CreateEntryDTO createExampleEntry() {
        return new CreateEntryDTO(1101, 100.0, BalanceType.Debit);
    }

    public static List<CreateEntryDTO> createSampleEntriesDTO() {
        final CreateEntryDTO debitEntry = new CreateEntryDTO(1101, 100.0, BalanceType.Debit);
        final CreateEntryDTO creditEntry = new CreateEntryDTO(4101, 100.0, BalanceType.Credit);
        return List.of(debitEntry, creditEntry);
    }
}