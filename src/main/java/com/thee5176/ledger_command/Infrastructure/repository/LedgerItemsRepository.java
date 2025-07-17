package com.thee5176.ledger_command.Infrastructure.repository;

import java.util.List;
import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_command.Domain.model.Tables;
import com.thee5176.ledger_command.Domain.model.tables.pojos.LedgerItems;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class LedgerItemsRepository {
    
    private final DSLContext dslContext;

    public void createLedgerItems(LedgerItems ledgerItems) {
        dslContext.insertInto(Tables.LEDGER_ITEMS, Tables.LEDGER_ITEMS.ID, Tables.LEDGER_ITEMS.LEDGER_ID, Tables.LEDGER_ITEMS.COA, Tables.LEDGER_ITEMS.AMOUNT, Tables.LEDGER_ITEMS.TYPE, Tables.LEDGER_ITEMS.CREATED_AT, Tables.LEDGER_ITEMS.UPDATED_AT)
            .values(ledgerItems.getId(), ledgerItems.getLedgerId(), ledgerItems.getCoa(), ledgerItems.getAmount(), ledgerItems.getType(), ledgerItems.getCreatedAt(), ledgerItems.getUpdatedAt())
            .execute();
    }

    public void updateLedgerItems(UUID uuid, LedgerItems ledgerItems) {
        dslContext.update(Tables.LEDGER_ITEMS)
            .set(Tables.LEDGER_ITEMS.LEDGER_ID, ledgerItems.getLedgerId())
            .set(Tables.LEDGER_ITEMS.AMOUNT, ledgerItems.getAmount())
            .set(Tables.LEDGER_ITEMS.COA, ledgerItems.getCoa())
            .set(Tables.LEDGER_ITEMS.TYPE, ledgerItems.getType())
            .set(Tables.LEDGER_ITEMS.UPDATED_AT, ledgerItems.getUpdatedAt())
            .where(Tables.LEDGER_ITEMS.ID.eq(uuid))
                .execute();
    }

    public void deleteLedgerItems(UUID uuid) {
        dslContext.delete(Tables.LEDGER_ITEMS)
            .where(Tables.LEDGER_ITEMS.ID.eq(uuid))
                .execute();
    }

    public List<LedgerItems> getLedgerItems() {
        return dslContext.selectFrom(Tables.LEDGER_ITEMS)
                .fetchInto(LedgerItems.class);
    }
}
