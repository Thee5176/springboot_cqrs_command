package com.thee5176.ledger_command.Application.dto;

import com.thee5176.ledger_command.Domain.model.enums.BalanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLedgerItemsDTO{
    Integer coa;
    Double amount;
    BalanceType type;
<<<<<<< HEAD
=======

    public Double getBalance() {
        return BalanceType.Debit.equals(type) ? amount : amount * -1;
    }
>>>>>>> feature/refactor-entity-name
}
