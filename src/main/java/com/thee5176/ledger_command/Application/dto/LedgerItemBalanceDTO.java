package com.thee5176.ledger_command.Application.dto;

import java.util.Date;
import com.thee5176.ledger_command.Domain.model.enums.BalanceType;

public record LedgerItemBalanceDTO(
    Date date, 
    Integer coa,
    Double balance,
    BalanceType accountBalanceType,
    BalanceType itemBalanceType
) {

}
