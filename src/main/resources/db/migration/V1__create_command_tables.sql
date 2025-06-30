CREATE TYPE "balance_type" AS ENUM (
  'Debit',
  'Credit'
);

CREATE TYPE "element" AS ENUM (
  'Assets',
  'Liabilities',
  'Equity',
  'Revenue',
  'Expenses',
  'Other'
);

CREATE TABLE "transactions" (
  "id" uuid PRIMARY KEY,
  "date" date NOT NULL,
  "description" varchar NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "entries" (
  "id" uuid PRIMARY KEY,
  "transaction_id" uuid NOT NULL,
  "coa" int NOT NULL,
  "amount" double precision NOT NULL,
  "type" balance_type NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);

CREATE TABLE "code_of_account" (
  "code" integer PRIMARY KEY NOT NULL,
  "title" varchar UNIQUE NOT NULL,
  "level" int NOT NULL,
  "element" element NOT NULL,
  "type" balance_type NOT NULL
);

COMMENT ON TABLE "transactions" IS '取引： 取引に少なくとも2つの科目が関係し、一つが借方 他のは貸方
https://www.geeksforgeeks.org/accountancy/accounting-entry-meaning-types-advantages-examples/
';

COMMENT ON TABLE "entries" IS 'エントリー：取引内の科目だけの関係で分ける
https://www.geeksforgeeks.org/accountancy/accounting-entry-meaning-types-advantages-examples/
';

COMMENT ON TABLE "code_of_account" IS '勘定科目(COA) - フレームワークの科目を活用する
- 青色申告: https://biz.moneyforward.com/tax_return/basic/12079/
- IFRS: https://www.ifrs-gaap.com/basic-ifrs-coa
- GAAP: https://www.ifrs-gaap.com/basic-us-gaap-coa
';

COMMENT ON COLUMN "code_of_account"."element" IS '勘定科目';

COMMENT ON COLUMN "code_of_account"."title" IS '科目名（例：現金、売掛金など）';

COMMENT ON COLUMN "code_of_account"."type" IS '借・貸';

ALTER TABLE "entries" ADD FOREIGN KEY ("coa") REFERENCES "code_of_account" ("code");