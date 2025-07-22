package com.thee5176.ledger_command.Domain.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thee5176.ledger_command.Application.dto.CreateLedgerDTO;
import com.thee5176.ledger_command.Application.mapper.LedgerItemsMapper;
import com.thee5176.ledger_command.Application.mapper.LedgerMapper;
import com.thee5176.ledger_command.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.ledger_command.Domain.model.tables.pojos.Ledgers;
import com.thee5176.ledger_command.Infrastructure.repository.LedgerItemsRepository;
import com.thee5176.ledger_command.Infrastructure.repository.LedgerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class LedgerCommandService {
    private final LedgerRepository ledgerRepository;

    private final LedgerItemsRepository ledgerItemRepository;

    private final LedgerMapper ledgerMapper;

    private final LedgerItemsMapper LedgerItemsMapper;

    @Transactional
    public void createLedger(CreateLedgerDTO createLedgerDTO) throws RuntimeException {
        final UUID ledger_uuid = UUID.randomUUID();

        // 取引作成stream
        Ledgers ledger = ledgerMapper.map(createLedgerDTO).setId(ledger_uuid);
        
        ledgerRepository.createLedger(ledger);
        log.info("Ledger created: {}", ledger);
        
        // 取引行別作成stream
        List<LedgerItems> ledgerItemsList = LedgerItemsMapper.map(createLedgerDTO);

        ledgerItemsList.forEach(ledgerItem -> {
            ledgerItem.setId(UUID.randomUUID());
            ledgerItem.setLedgerId(ledger_uuid);
            log.info("ledgerItem created: {}", ledgerItem);
            ledgerItemRepository.createLedgerItems(ledgerItem);
        });

    }

    @Transactional
    public void updateLedger(CreateLedgerDTO createLedgerDTO) {
        if (createLedgerDTO.getId() == null) {
            throw new IllegalArgumentException("Ledger ID must not be null for update.");
        }
        
        List<LedgerItems> ledgerItemsUpdatedList = LedgerItemsMapper.map(createLedgerDTO);
        List<LedgerItems> existingLedgerItems = ledgerItemRepository.getLedgerItemsByLedgerId(createLedgerDTO.getId());

        //Test Case:
        // - add new itemLedgers?
        // - delete itemLedgers?

        // Update existing ledger items or create new ones
        ledgerItemsUpdatedList.stream().forEach(ledgerItems -> {
            if (ledgerItems.getId() == null) {
                ledgerItems.setId(UUID.randomUUID());
            }
            ledgerItems.setLedgerId(createLedgerDTO.getId());
            ledgerItemRepository.updateLedgerItems(ledgerItems);
        });

        // Delete ledger items that are not in the updated list
        existingLedgerItems.stream()
                .filter(existingItem -> ledgerItemsUpdatedList.stream()
                        .noneMatch(updatedItem -> updatedItem.getId().equals(existingItem.getId()))
                        )
                .forEach(itemToDelete -> {
                    ledgerItemRepository.deleteLedgerItems(itemToDelete.getId());
                    log.info("Ledger item deleted: {}", itemToDelete);
                });

        log.info("Ledger items updated: {}", ledgerItemsUpdatedList);

        Ledgers ledgers = ledgerMapper.map(createLedgerDTO);
        ledgerRepository.updateLedger(ledgers);
        log.info("Ledger updated: {}", ledgers);
    }

    @Transactional
    public void deleteLedger(UUID uuid) {
        // cascade delete apply in DB layer
        ledgerRepository.deleteLedger(uuid);
        log.info("Ledger deleted: {}", uuid);
    }
}
