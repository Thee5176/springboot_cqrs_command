package com.thee5176.record.springboot_cqrs_command.Application.dto;

import com.thee5176.record.springboot_cqrs_command.Domain.model.enums.BalanceType;
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
}
