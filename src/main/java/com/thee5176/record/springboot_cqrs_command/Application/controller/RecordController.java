package com.thee5176.record.springboot_cqrs_command.Application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.record.springboot_cqrs_command.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_command.Domain.service.RecordCommandService;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/record")
@AllArgsConstructor
public class RecordController {
    private final RecordCommandService recordCommandService;

    @PostMapping
    public ResponseEntity<String> newBookrecord(@RequestBody CreateRecordDTO createRecordDTO) {
        recordCommandService.createRecord(createRecordDTO);

        return ResponseEntity.ok("Succesfully create new bookrecord");
    }

    // TODO : UPDATE and DELETE endpoints
}