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
    public void updateLedger(CreateLedgerDTO createLedgerDTO) {  //LedgerRegistrationDTO LedgerEntryDTO
        if (createLedgerDTO.getId() == null) {
            throw new IllegalArgumentException("Ledger ID must not be null for update.");
        }
        List<LedgerItems> existingLedgerItemsList = ledgerItemRepository.getLedgerItemsByLedgerId(createLedgerDTO.getId());
        List<LedgerItems> ledgerItemsUpdateList = LedgerItemsMapper.map(createLedgerDTO);
        
        // Stream1# : Matched Accounting Code -> Assign ID to LedgerItems -> Update LedgerItems
        // Stream1# : 一致する勘定科目コード -> IDをLedgerItems割り当て -> LedgerItemsを更新
        ledgerItemsUpdateList.forEach(updateItem ->
            existingLedgerItemsList.stream()
            .filter(existingItem -> existingItem.getCoa().equals(updateItem.getCoa()))
            .findFirst()
            .ifPresent(existingItem -> {
                updateItem.setId(existingItem.getId());
                updateItem.setLedgerId(existingItem.getLedgerId());
                log.info("Ledger item updated: {}", updateItem);
                ledgerItemRepository.updateLedgerItems(updateItem);
            }
            )
        );

        // Stream2# : Create new LedgerItems that not found in Stream#1
        // Stream2# : Stream#1で見つからなかったLedgerItemsを作成
        ledgerItemsUpdateList.stream().forEach(ledgerItems -> {
            if (ledgerItems.getId() == null) {
            ledgerItems.setId(UUID.randomUUID());
            ledgerItems.setLedgerId(createLedgerDTO.getId());
            log.info("New ledger item created: {}", ledgerItems);
            ledgerItemRepository.createLedgerItems(ledgerItems);
            }
        });

        // Stream3# : Delete ledger items that are not in the updated list
        // Stream3# : 更新リストに存在しないLedgerItemsを削除
        existingLedgerItemsList.stream()
            .filter(existingItem -> ledgerItemsUpdateList.stream()
            .noneMatch(updatedItem -> updatedItem.getId() != null && updatedItem.getId().equals(existingItem.getId()))
            )
            .forEach(itemToDelete -> {
            ledgerItemRepository.deleteLedgerItems(itemToDelete.getId());
            log.info("Ledger item deleted: {}", itemToDelete);
            }
        );

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
