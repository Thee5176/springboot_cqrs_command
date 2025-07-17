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
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.record.springboot_cqrs_command.Infrastructure.repository.LedgerItemsRepository;
import lombok.AllArgsConstructor;




@RestController
@RequestMapping("/ledger_items")
@AllArgsConstructor
public class LedgerItemsController {
    //Repository確認用Controller
    private final LedgerItemsRepository LedgerItemsMapper;

    @GetMapping
    public List<LedgerItems> getLedgerItems() {
        return LedgerItemsMapper.getLedgerItems();
    }

    @PostMapping
    public ResponseEntity<String> createLedgerItems(@RequestParam LedgerItems ledgerItems) {
        LedgerItemsMapper.createLedgerItems(ledgerItems);
        
        return ResponseEntity.ok("created ledgerItems succesfully");
    }
    

    @PutMapping
    public ResponseEntity<String> updateLedgerItems(@RequestParam UUID uuid, @RequestBody LedgerItems ledgerItems) {
        LedgerItemsMapper.updateLedgerItems(uuid, ledgerItems);

        return ResponseEntity.ok("updated ledgerItems succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLedgerItems(@RequestParam UUID uuid) {
        LedgerItemsMapper.deleteLedgerItems(uuid);

        return ResponseEntity.ok("deleted ledgerItems succesfully");
    }
}
