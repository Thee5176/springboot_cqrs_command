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
    @Schema(example = "5101", allowableValues = {"1101","5101"})
    Integer coa;
    @Schema(example = "550.50")
    Double amount;
    @Schema(example = "Debit", allowableValues = {"Debit", "Credit"})
    BalanceType type;

    @Schema(hidden = true)
    public Double getBalance() {
        return BalanceType.Debit.equals(type) ? amount : amount * -1;
    }
}
