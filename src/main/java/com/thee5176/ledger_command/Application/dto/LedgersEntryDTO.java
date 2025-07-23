package com.thee5176.ledger_command.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import io.swagger.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;
import com.thee5176.ledger_command.Application.validation.BalanceCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedgersEntryDTO {
    @Nullable
    UUID id;

    @PastOrPresent(message = "Date must be in the past or present.")
    LocalDate date;

    @NotBlank
    String description;

    @Size(min = 2, message = "The list must contain at least 2 items.")
    @BalanceCheck
    @Schema(implementation = LedgerItemsEntryDTO.class)
    List<LedgerItemsEntryDTO> ledgerItems;
    
    @PastOrPresent(message = "Timestamp must be in the past or present.")
    LocalDateTime timestamp;
}