package com.thee5176.record.springboot_cqrs_command.Application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Ledgers;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.LedgerRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ledgers")
@RequiredArgsConstructor
public class LedgerController {
    //Repository確認用Controller
    private final LedgerRepository ledgerRepository;

    @PostMapping
    public ResponseEntity<String> createLedger(@RequestBody Ledgers ledger) {
        ledgerRepository.createLedger(ledger);
        
        return ResponseEntity.ok("Ledger created succesfully");
    }

    @PutMapping
    public ResponseEntity<String> updateLedger(@RequestParam UUID uuid, @RequestBody Ledgers ledger) {
        ledgerRepository.updateLedger(uuid, ledger);
        
        return ResponseEntity.ok("Ledger updated succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLedger(@RequestParam UUID uuid) {
        ledgerRepository.deleteLedger(uuid);

        return ResponseEntity.ok("Ledger deleted succesfully");
    }


    @GetMapping
    public List<Ledgers> getLedger() {
        return ledgerRepository.getLedgers();
    }
}
