CREATE TABLE IF NOT EXISTS bank (
    id                  UUID    NOT NULL,
    name                TEXT    NOT NULL,
    legal_entity_charge NUMERIC NOT NULL,
    individual_charge   NUMERIC NOT NULL,
    CONSTRAINT pk_bank PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account (
    bank_id   UUID    NOT NULL,
    client_id UUID    NOT NULL,
    name      TEXT    NOT NULL,
    currency  CHAR(3) NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (bank_id, client_id, currency)
);

CREATE TABLE IF NOT EXISTS client (
    id   UUID NOT NULL,
    name TEXT NOT NULL,
    type TEXT NOT NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);
