package com.thee5176.ledger_command.Application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.thee5176.ledger_command.Domain.model.enums.BalanceType;

public class CreateLedgerItemsDTOTest {

    // Test data for a debit ledgerItems (e.g., for an asset or expense account)
    // Arrange
    final Integer coaDebit = 1101; // Example Chart of Accounts code for "Cash"
    final Double amountDebit = 250.50;
    final BalanceType typeDebit = BalanceType.Debit;

    //Act
    final CreateLedgerItemsDTO debitLedgerItems = new CreateLedgerItemsDTO(coaDebit, amountDebit, typeDebit);
    
    
    // Test data for a credit ledgerItems (e.g., for a liability, equity, or revenue account)
    // Arrange
    final Integer coaCredit = 4101; // Example Chart of Accounts code for "Sales Revenue"
    final Double amountCredit = 250.50;
    final BalanceType typeCredit = BalanceType.Credit;
    
    //Act
    final CreateLedgerItemsDTO creditLedgerItems = new CreateLedgerItemsDTO(coaCredit, amountCredit, typeCredit);

    @Test
    void canCreateDebitLedgerItems() {
        //Assert
        assertNotNull(debitLedgerItems);
        assertEquals(coaDebit, debitLedgerItems.getCoa());
        assertEquals(amountDebit, debitLedgerItems.getAmount());
        assertEquals(typeDebit, debitLedgerItems.getType());
    }

    @Test
    void canCreateCreditLedgerItems() {
        //Assert
        assertNotNull(creditLedgerItems);
        assertEquals(coaCredit, creditLedgerItems.getCoa());
        assertEquals(amountCredit, creditLedgerItems.getAmount());
        assertEquals(typeCredit, creditLedgerItems.getType());
    }

    @Test
    void testGetBalanceMethod() {
        //Assert
        assertEquals(amountDebit, debitLedgerItems.getBalance());
        assertEquals(amountCredit  * -1, creditLedgerItems.getBalance());
    }

    public static CreateLedgerItemsDTO createOneSampleCreateLedgerItemsDTO() {
        return new CreateLedgerItemsDTO(1101, 100.0, BalanceType.Debit);
    }

    public static List<CreateLedgerItemsDTO> createBalancedSampleCreateLedgerItemsDTO() {
        // This creates a balanced ledger with one debit and one credit
        final CreateLedgerItemsDTO debitLedgerItems = new CreateLedgerItemsDTO(1101, 100.0, BalanceType.Debit);
        final CreateLedgerItemsDTO creditLedgerItems = new CreateLedgerItemsDTO(4101, 100.0, BalanceType.Credit);

        return List.of(debitLedgerItems, creditLedgerItems);
    }

    public static List<CreateLedgerItemsDTO> createImalancedSampleCreateLedgerItemsDTO() {
        // This creates an imbalanced ledger with one debit and one credit
        final CreateLedgerItemsDTO debitLedgerItems = new CreateLedgerItemsDTO(1101, 0.0, BalanceType.Debit);
        final CreateLedgerItemsDTO creditLedgerItems = new CreateLedgerItemsDTO(4101, 100.0, BalanceType.Credit);
        
        return List.of(debitLedgerItems, creditLedgerItems);
    }
}