package com.thee5176.record.springboot_cqrs_command.Application.dto;

import java.util.UUID;
import com.thee5176.record.springboot_cqrs_command.Domain.model.enums.BalanceType;

public record CreateEntryDTO(
    UUID id,
    UUID transactionId,
    Integer coa,
    Double amount,
    BalanceType type
) {
    
}
