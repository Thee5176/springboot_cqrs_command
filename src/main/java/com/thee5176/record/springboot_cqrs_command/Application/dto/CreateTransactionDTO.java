package com.thee5176.record.springboot_cqrs_command.Application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionDTO(
    UUID id,
    LocalDate date,
    String description
) {

}
