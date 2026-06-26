CREATE TABLE rates
(
    id            serial4 NOT NULL PRIMARY KEY,
    from_currency CHAR(3) NOT NULL,
    to_currency   CHAR(3) NOT NULL,
    rate          NUMERIC NOT NULL,
    CONSTRAINT uq_exchange_rate_pair_time UNIQUE (from_currency, to_currency)
);