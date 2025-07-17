package com.thee5176.record.springboot_cqrs_command.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecordDTO {
    @Nullable
    UUID id;

    @PastOrPresent(message = "Date must be in the past or present.")
    LocalDate date;

    @NotBlank
    String description;

    @Size(min = 2, message = "The list must contain at least 2 items.")
    List<CreateLedgerItemsDTO> ledgerItems;
    
    @PastOrPresent(message = "Timestamp must be in the past or present.")
    LocalDateTime timestamp;
}