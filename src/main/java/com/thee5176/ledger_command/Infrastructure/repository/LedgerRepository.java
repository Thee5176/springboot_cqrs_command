package com.thee5176.ledger_command.Infrastructure.repository;

import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_command.Domain.model.Tables;
import com.thee5176.ledger_command.Domain.model.tables.pojos.Ledgers;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class LedgerRepository {
    
    private final DSLContext dslContext;

    //Create - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row
    public void createLedger(Ledgers ledgers) {
        dslContext.insertInto(Tables.LEDGERS, Tables.LEDGERS.ID, Tables.LEDGERS.DATE, Tables.LEDGERS.DESCRIPTION,Tables.LEDGERS.CREATED_AT, Tables.LEDGERS.UPDATED_AT)
            .values(ledgers.getId(), ledgers.getDate(), ledgers.getDescription(), ledgers.getCreatedAt(), ledgers.getUpdatedAt())
                .execute();
    }

    //Update - https://www.jooq.orghttps://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row
    public void updateLedger(UUID uuid, Ledgers Ledgers) {
        dslContext.update(Tables.LEDGERS)
            .set(Tables.LEDGERS.DATE, Ledgers.getDate())
            .set(Tables.LEDGERS.DESCRIPTION, Ledgers.getDescription())
            .set(Tables.LEDGERS.CREATED_AT, Ledgers.getCreatedAt())
            .set(Tables.LEDGERS.UPDATED_AT, Ledgers.getUpdatedAt())
            .where(Tables.LEDGERS.ID.eq(uuid))
                .execute();
    }
    
    //Delete - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/delete-statement/
    public void deleteLedger(UUID uuid) {
        dslContext.delete(Tables.LEDGERS)
        .where(Tables.LEDGERS.ID.eq(uuid))
            .execute();
    }
}
