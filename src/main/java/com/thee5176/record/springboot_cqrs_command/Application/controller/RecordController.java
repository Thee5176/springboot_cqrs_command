package com.thee5176.record.springboot_cqrs_command.Application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.service.RecordCommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/record")
@AllArgsConstructor
public class RecordController {
    private final RecordCommandService recordCommandService;

    @PostMapping
    public ResponseEntity<String> newBookrecord(@RequestBody CreateRecordDTO createRecordDTO) {
        try {
            recordCommandService.createRecord(createRecordDTO);
            log.info("New bookrecord created: {}", createRecordDTO);
        } catch (Exception e) {
            log.error("Error creating bookrecord: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to create bookrecord: " + e.getMessage());
        }
        return ResponseEntity.ok("Succesfully create new bookrecord");
    }

    // TODO : UPDATE and DELETE endpoints
}