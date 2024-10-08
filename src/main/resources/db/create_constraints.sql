ALTER TABLE bank
    DROP CONSTRAINT IF EXISTS uk_bank;

ALTER TABLE account
    DROP CONSTRAINT IF EXISTS fk_account_bank,
    DROP CONSTRAINT IF EXISTS fk_account_client,
    DROP CONSTRAINT IF EXISTS uk_account;

ALTER TABLE bank
    ADD CONSTRAINT uk_bank UNIQUE (name);

ALTER TABLE account
    ADD CONSTRAINT fk_account_bank FOREIGN KEY (bank_id) REFERENCES bank(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_account_client FOREIGN KEY (client_id) REFERENCES client(id),
    ADD CONSTRAINT uk_account UNIQUE (bank_id, client_id, currency);
