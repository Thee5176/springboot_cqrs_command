package com.thee5176.record.springboot_cqrs_command.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecordDTO {
    UUID id;
    LocalDate date;
    String description;
    List<CreateEntryDTO> entries;
    LocalDateTime timestamp;
    
}