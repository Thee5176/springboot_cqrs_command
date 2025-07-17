ALTER TABLE transactions
    RENAME TO ledgers;

ALTER TABLE entries
    RENAME TO ledger_items;