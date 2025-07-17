package com.thee5176.ledger_command.Application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_command.Application.dto.CreateLedgerDTO;
import com.thee5176.ledger_command.Domain.service.LedgerCommandService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/ledger")
@AllArgsConstructor
public class LedgerController {
    private final LedgerCommandService ledgerCommandService;

    @PostMapping
    public ResponseEntity<String> newLedger(@RequestBody @Validated CreateLedgerDTO createLedgerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors());
        }
        
        try {
            ledgerCommandService.createLedger(createLedgerDTO);
            log.info("New ledger created: {}", createLedgerDTO);
        } catch (Exception e) {
            log.error("Error creating ledger: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to create ledger: " + e.getMessage());
        }
        return ResponseEntity.ok("Successfully created new ledger");
    }

    // TODO : UPDATE and DELETE endpoints
}