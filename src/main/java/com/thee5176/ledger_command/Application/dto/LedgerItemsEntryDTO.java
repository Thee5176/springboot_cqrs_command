package com.thee5176.ledger_command.Application.dto;

import com.thee5176.ledger_command.Domain.model.enums.BalanceType;
import io.swagger.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedgerItemsEntryDTO{

    Integer coa;
    Double amount;
    BalanceType type;

    @Schema(hidden = true)
    public Double getBalance() {
        return BalanceType.Debit.equals(type) ? amount : amount * -1;
    }
}