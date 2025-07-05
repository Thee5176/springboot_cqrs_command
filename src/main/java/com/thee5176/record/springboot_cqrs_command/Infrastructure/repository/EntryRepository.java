package com.thee5176.record.springboot_cqrs_command.Infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.thee5176.record.springboot_cqrs_command.Domain.model.Tables;
import com.thee5176.record.springboot_cqrs_command.Domain.model.tables.pojos.Entries;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EntryRepository {
    
    private final DSLContext dslContext;

    public void createEntry(Entries entries) {
        dslContext.insertInto(Tables.ENTRIES, Tables.ENTRIES.ID, Tables.ENTRIES.TRANSACTION_ID, Tables.ENTRIES.COA, Tables.ENTRIES.AMOUNT, Tables.ENTRIES.TYPE, Tables.ENTRIES.CREATED_AT, Tables.ENTRIES.UPDATED_AT)
            .values(entries.getId(), entries.getTransactionId(), entries.getCoa(), entries.getAmount(), entries.getType(), entries.getCreatedAt(), entries.getUpdatedAt())
            .execute();
    }

    public void updateEntry(@RequestParam UUID uuid, @RequestBody Entries entries) {
        dslContext.update(Tables.ENTRIES)
            .set(Tables.ENTRIES.TRANSACTION_ID, entries.getTransactionId())
            .set(Tables.ENTRIES.AMOUNT, entries.getAmount())
            .set(Tables.ENTRIES.COA, entries.getCoa())
            .set(Tables.ENTRIES.TYPE, entries.getType())
            .set(Tables.ENTRIES.CREATED_AT, entries.getCreatedAt())
            .set(Tables.ENTRIES.UPDATED_AT, entries.getUpdatedAt())
            .where(Tables.ENTRIES.ID.eq(uuid))
                .execute();
    }

    public void deleteEntry(UUID uuid) {
        dslContext.delete(Tables.ENTRIES)
            .where(Tables.ENTRIES.ID.eq(uuid))
                .execute();
    }

    public List<Entries> getEntry() {
        return dslContext.selectFrom(Tables.TRANSACTIONS)
                .fetchInto(Entries.class);
    }
}
