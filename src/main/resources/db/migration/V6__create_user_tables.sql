CREATE SCHEMA IF NOT EXISTS credential;

CREATE TABLE credential.authorities (
    authority VARCHAR(255) PRIMARY KEY
);

CREATE TABLE credential.user (
    "id" bigint PRIMARY KEY NOT NULL,
    "username" varchar NOT NULL,
    "password" varchar NOT NULL,
    "is_enabled" boolean,
    "is_account_non_expired" boolean,
    "is_credentials_non_expired" boolean,
    "is_account_non_locked" boolean,
    "authorities" VARCHAR(255)[] NOT NULL,

    "firstname" VARCHAR(255) NOT NULL,
    "lastname" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL
);

ALTER TABLE credential.user ADD FOREIGN KEY ("authorities") REFERENCES credential.authorities ("authority") ON UPDATE CASCADE;

INSERT INTO credential.user ("id", "username", "password", "is_enabled", "is_account_non_expired", "is_credentials_non_expired", "is_account_non_locked", "firstname", "lastname", "email", "authorities")
VALUES (1, 'user', '$2a$12$BakliHkFb3CcDhwl6e3WmOhJC1IVYipyLUKRwVeXkAucghMXxubGK', true, true, true, true, 'John', 'Doe', 'john.doe@example.com', ARRAY['USER']::VARCHAR(255)[]);


ALTER TABLE accounting.ledgers ADD COLUMN "owner_id" bigint NOT NULL;
ALTER TABLE accounting.ledgers ADD CONSTRAINT "ledger_items_user_fkey" FOREIGN KEY ("owner_id") REFERENCES credential.user ("id") ON DELETE SET NULL;