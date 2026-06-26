CREATE TABLE rates
(
    id            serial4 NOT NULL PRIMARY KEY,
    from_currency CHAR(3) NOT NULL,
    to_currency   CHAR(3) NOT NULL,
    rate          NUMERIC NOT NULL,
    CONSTRAINT uq_exchange_rate_pair_time UNIQUE (from_currency, to_currency)
);

INSERT INTO rates (from_currency, to_currency, rate) VALUES
('USD', 'EUR', 0.88500000),
('USD', 'GBP', 0.78700000),
('USD', 'AUD', 1.56200000),
('USD', 'NOK', 10.63000000),
('EUR', 'USD', 1.13000000),
('EUR', 'GBP', 0.88900000),
('EUR', 'AUD', 1.76500000),
('EUR', 'NOK', 12.01000000),
('GBP', 'USD', 1.27000000),
('GBP', 'EUR', 1.12500000),
('GBP', 'AUD', 1.98600000),
('GBP', 'NOK', 13.51000000),
('AUD', 'USD', 0.64000000),
('AUD', 'EUR', 0.56700000),
('AUD', 'GBP', 0.50400000),
('AUD', 'NOK', 6.80000000),
('NOK', 'USD', 0.09400000),
('NOK', 'EUR', 0.08300000),
('NOK', 'GBP', 0.07400000),
('NOK', 'AUD', 0.14700000);
