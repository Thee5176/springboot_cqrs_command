package com.thee5176.ledger_command.Application.controller;

<<<<<<< HEAD
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
=======
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
>>>>>>> feature/refactor-entity-name
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestParam;
=======
>>>>>>> feature/refactor-entity-name
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_command.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.ledger_command.Infrastructure.repository.LedgerItemsRepository;
import lombok.AllArgsConstructor;




@RestController
@RequestMapping("/ledger_items")
@AllArgsConstructor
public class LedgerItemsController {
<<<<<<< HEAD
    //Repository確認用Controller
    private final LedgerItemsRepository LedgerItemsMapper;

    @GetMapping
    public List<LedgerItems> getLedgerItems() {
        return LedgerItemsMapper.getLedgerItems();
    }

    @PostMapping
    public ResponseEntity<String> createLedgerItems(@RequestParam LedgerItems ledgerItems) {
        LedgerItemsMapper.createLedgerItems(ledgerItems);
=======
    private final LedgerItemsRepository ledgerItemsRepository;

    @PostMapping
    public ResponseEntity<String> createLedgerItems(@RequestBody LedgerItems ledgerItems) {
        ledgerItemsRepository.createLedgerItems(ledgerItems);
>>>>>>> feature/refactor-entity-name
        
        return ResponseEntity.ok("created ledgerItems succesfully");
    }
    

    @PutMapping
<<<<<<< HEAD
    public ResponseEntity<String> updateLedgerItems(@RequestParam UUID uuid, @RequestBody LedgerItems ledgerItems) {
        LedgerItemsMapper.updateLedgerItems(uuid, ledgerItems);
=======
    public ResponseEntity<String> updateLedgerItems(@RequestBody UUID uuid, @RequestBody LedgerItems ledgerItems) {
        ledgerItemsRepository.updateLedgerItems(uuid, ledgerItems);
>>>>>>> feature/refactor-entity-name

        return ResponseEntity.ok("updated ledgerItems succesfully");
    }

    @DeleteMapping
<<<<<<< HEAD
    public ResponseEntity<String> deleteLedgerItems(@RequestParam UUID uuid) {
        LedgerItemsMapper.deleteLedgerItems(uuid);
=======
    public ResponseEntity<String> deleteLedgerItems(@RequestBody UUID uuid) {
        ledgerItemsRepository.deleteLedgerItems(uuid);
>>>>>>> feature/refactor-entity-name

        return ResponseEntity.ok("deleted ledgerItems succesfully");
    }
}
