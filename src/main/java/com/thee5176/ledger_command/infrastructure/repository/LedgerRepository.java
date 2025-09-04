package com.thee5176.ledger_command.infrastructure.repository;

import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_command.application.exception.JooqOperationException;
import com.thee5176.ledger_command.domain.model.accounting.Tables;
import com.thee5176.ledger_command.domain.model.accounting.tables.pojos.Ledgers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@AllArgsConstructor
public class LedgerRepository {
    
    private final DSLContext dslContext;

    //Create - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row
    public void createLedger(Ledgers ledgers) {
        try {
            dslContext.insertInto(Tables.LEDGERS, Tables.LEDGERS.ID, Tables.LEDGERS.DATE, Tables.LEDGERS.DESCRIPTION,Tables.LEDGERS.CREATED_AT, Tables.LEDGERS.UPDATED_AT)
                .values(ledgers.getId(), ledgers.getDate(), ledgers.getDescription(), ledgers.getCreatedAt(), ledgers.getUpdatedAt())
                    .execute();
        } catch (Exception e) {
            log.error("Error creating ledger", e);
            throw new JooqOperationException("Failed to create Ledger");
        }
    }

    //Update - https://www.jooq.orghttps://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row
    public void updateLedger(Ledgers Ledgers) {
        try {
            dslContext.update(Tables.LEDGERS)
                .set(Tables.LEDGERS.DATE, Ledgers.getDate())
                .set(Tables.LEDGERS.DESCRIPTION, Ledgers.getDescription())
                .set(Tables.LEDGERS.UPDATED_AT, Ledgers.getUpdatedAt())
                .where(Tables.LEDGERS.ID.eq(Ledgers.getId()))
                    .execute();
        } catch (Exception e) {
            log.error("Error deleting ledger", e);
            throw new JooqOperationException("Failed to delete Ledger");
        }
    }
    
    //Delete - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/delete-statement/
    public void deleteLedger(UUID uuid) {
        try {
            dslContext.delete(Tables.LEDGERS)
            .where(Tables.LEDGERS.ID.eq(uuid))
                .execute();
        } catch (Exception e) {
            log.error("Error deleting ledger", e);
            throw new JooqOperationException("Failed to delete Ledger");
        }

    }

}
