CREATE TABLE accounts
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

CREATE TABLE balances
(
    id         UUID PRIMARY KEY        DEFAULT gen_random_uuid(),
    client_id  UUID           NOT NULL REFERENCES accounts (id),
    currency   VARCHAR(3)     NOT NULL,
    amount     NUMERIC(18, 4) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP      NOT NULL,
    CONSTRAINT client_currency UNIQUE (client_id, currency)
);

CREATE TABLE conversions
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id       UUID           NOT NULL REFERENCES accounts (id),
    source_amount   NUMERIC(18, 4) NOT NULL,
    source_currency VARCHAR(3)     NOT NULL,
    target_currency VARCHAR(3)     NOT NULL,
    target_amount   NUMERIC(18, 4) NOT NULL,
    exchange_rate   NUMERIC(18, 4) NOT NULL,
    idempotency_key UUID UNIQUE    NOT NULL,
    created_at      TIMESTAMP      NOT NULL
);