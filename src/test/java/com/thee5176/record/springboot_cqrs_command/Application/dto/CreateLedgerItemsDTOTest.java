package com.thee5176.record.springboot_cqrs_command.Application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.thee5176.record.springboot_cqrs_command.Domain.model.enums.BalanceType;

public class CreateLedgerItemsDTOTest {

    @Test
    void canCreateDebitLedgerItems() {
        // Test data for a debit ledgerItems (e.g., for an asset or expense account)
        // Arrange
        final Integer coa = 1101; // Example Chart of Accounts code for "Cash"
        final Double amount = 250.50;
        final BalanceType type = BalanceType.Debit;

        //Act
        CreateLedgerItemsDTO debitLedgerItems = new CreateLedgerItemsDTO(coa, amount, type);
        
        //Assert
        assertNotNull(debitLedgerItems);
        assertEquals(coa, debitLedgerItems.getCoa());
        assertEquals(amount, debitLedgerItems.getAmount());
        assertEquals(type, debitLedgerItems.getType());
    }

    @Test
    void canCreateCreditLedgerItems() {
        // Test data for a credit ledgerItems (e.g., for a liability, equity, or revenue account)
        // Arrange
        final Integer coa = 4101; // Example Chart of Accounts code for "Sales Revenue"
        final Double amount = 250.50;
        final BalanceType type = BalanceType.Credit;

        //Act
        CreateLedgerItemsDTO creditLedgerItems = new CreateLedgerItemsDTO(coa, amount, type);

        //Assert
        assertNotNull(creditLedgerItems);
        assertEquals(coa, creditLedgerItems.getCoa());
        assertEquals(amount, creditLedgerItems.getAmount());
        assertEquals(type, creditLedgerItems.getType());
}

    public static CreateLedgerItemsDTO createSampleCreateLedgerItemsDTO() {
        return new CreateLedgerItemsDTO(1101, 100.0, BalanceType.Debit);
    }

    public static List<CreateLedgerItemsDTO> createPairSampleCreateLedgerItemsDTO() {
        final CreateLedgerItemsDTO debitLedgerItems = new CreateLedgerItemsDTO(1101, 100.0, BalanceType.Debit);
        final CreateLedgerItemsDTO creditLedgerItems = new CreateLedgerItemsDTO(4101, 100.0, BalanceType.Credit);
        return List.of(debitLedgerItems, creditLedgerItems);
    }
}