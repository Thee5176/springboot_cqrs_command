-- For PostgreSQL, run this SQL to alter your table so the "id" column auto-generates a UUID:
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE public.transactions
ALTER COLUMN id SET DEFAULT uuid_generate_v4();

ALTER TABLE public.entries
ALTER COLUMN id SET DEFAULT uuid_generate_v4();