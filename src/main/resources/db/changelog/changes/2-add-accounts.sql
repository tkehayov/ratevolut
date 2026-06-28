INSERT INTO accounts (name, created_at)
VALUES ('Ivan', CURRENT_TIMESTAMP),
       ('Peter', CURRENT_TIMESTAMP),
       ('Mariya', CURRENT_TIMESTAMP);

INSERT INTO balances (client_id, currency, amount, created_at)
VALUES ((select id from accounts where name = 'Ivan'), 'AUD', 5000.00, CURRENT_TIMESTAMP),
       ((select id from accounts where name = 'Ivan'), 'EUR', 1200.50, CURRENT_TIMESTAMP),

       ((select id from accounts where name = 'Peter'), 'USD', 10000.00, CURRENT_TIMESTAMP),
       ((select id from accounts where name = 'Peter'), 'EUR', 450.00, CURRENT_TIMESTAMP),

       ((select id from accounts where name = 'Mariya'), 'CAD', 250.00, CURRENT_TIMESTAMP),
       ((select id from accounts where name = 'Mariya'), 'USD', 1500.75, CURRENT_TIMESTAMP);