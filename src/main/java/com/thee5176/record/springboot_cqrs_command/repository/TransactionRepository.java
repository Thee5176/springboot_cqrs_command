package com.thee5176.record.springboot_cqrs_command.repository;

import java.util.List;
import java.util.UUID;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thee5176.record.springboot_cqrs_command.model.Tables;
import com.thee5176.record.springboot_cqrs_command.model.tables.pojos.Transactions;

@Repository
public class TransactionRepository {
    
    @Autowired
    private DSLContext dslContext;

    //Create - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row
    public void createTransaction(Transactions transactions) {
        dslContext.insertInto(Tables.TRANSACTIONS, Tables.TRANSACTIONS.ID, Tables.TRANSACTIONS.DATE, Tables.TRANSACTIONS.DESCRIPTION,Tables.TRANSACTIONS.CREATED_AT, Tables.TRANSACTIONS.UPDATED_AT)
            .values(transactions.getId(), transactions.getDate(), transactions.getDescription(), transactions.getCreatedAt(), transactions.getUpdatedAt())
                .execute();
    }

    //Update - https://www.jooq.orghttps://www.jooq.org/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row/doc/latest/manual/sql-building/sql-statements/insert-statement/insert-values/#insert-values-with-a-single-row
    public void updateTransaction(UUID uuid, Transactions Transactions) {
        dslContext.update(Tables.TRANSACTIONS)
            .set(Tables.TRANSACTIONS.DATE, Transactions.getDate())
            .set(Tables.TRANSACTIONS.DESCRIPTION, Transactions.getDescription())
            .set(Tables.TRANSACTIONS.CREATED_AT, Transactions.getCreatedAt())
            .set(Tables.TRANSACTIONS.UPDATED_AT, Transactions.getUpdatedAt())
            .where(Tables.TRANSACTIONS.ID.eq(uuid))
                .execute();
    }
    
    //Delete - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/delete-statement/
    public void deleteTransaction(UUID uuid) {
        dslContext.delete(Tables.TRANSACTIONS)
        .where(Tables.TRANSACTIONS.ID.eq(uuid))
            .execute();
    }

    //Read - https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/select-statement/#select-from-single-tables
    public List<Transactions> getTransactions() {
        return dslContext.selectFrom(Tables.TRANSACTIONS)
                .fetchInto(Transactions.class);
    }
}
