INSERT INTO accounts (id, name, created_at)
VALUES ('1b8b13d9-76d3-4b54-9322-4bda96cbb2d4', 'Ivan', CURRENT_TIMESTAMP),
       ('36c58bb7-4809-44e9-bf8b-17ed473a104c', 'Peter', CURRENT_TIMESTAMP),
       ('4b43cf01-fd6b-4fbd-b749-020ed1444552', 'Mariya', CURRENT_TIMESTAMP);

INSERT INTO balances (client_id, currency, amount, created_at)
VALUES ('1b8b13d9-76d3-4b54-9322-4bda96cbb2d4', 'AUD', 100.00, CURRENT_TIMESTAMP),
       ('1b8b13d9-76d3-4b54-9322-4bda96cbb2d4', 'EUR', 1200.50, CURRENT_TIMESTAMP),

       ('36c58bb7-4809-44e9-bf8b-17ed473a104c', 'USD', 10000.00, CURRENT_TIMESTAMP),
       ('36c58bb7-4809-44e9-bf8b-17ed473a104c', 'EUR', 450.00, CURRENT_TIMESTAMP),

       ('4b43cf01-fd6b-4fbd-b749-020ed1444552', 'CAD', 250.00, CURRENT_TIMESTAMP),
       ('4b43cf01-fd6b-4fbd-b749-020ed1444552', 'USD', 1500.75, CURRENT_TIMESTAMP);

INSERT INTO conversions (id, client_id, source_amount, source_currency, target_currency, target_amount,
                                exchange_rate, created_at)
VALUES ('6bd9c18c-9cef-4b5e-be7b-7e0cc6eaa68a'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 10.0000, 'AUD',
        'EUR', 6.1000, 0.6100, '2026-06-28 09:11:45.405514'),
       ('a0261434-f9d2-4bfe-9d6d-1046ecabc518'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 10.0000, 'AUD',
        'EUR', 6.1000, 0.6100, '2026-06-28 09:14:44.873761'),
       ('1da4afeb-4dd6-4266-8530-c8604ee6428f'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 10.0000, 'AUD',
        'EUR', 6.1000, 0.6100, '2026-06-28 09:21:50.81871'),
       ('2fa76665-1062-4ff0-9259-ab0a63462ebe'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 10.0000, 'AUD',
        'EUR', 6.1000, 0.6100, '2026-06-28 09:27:50.511374'),
       ('8bfbcea8-2d07-4fea-9d09-53d1883548d4'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 10.0000, 'AUD',
        'EUR', 6.1000, 0.6100, '2026-06-28 09:55:45.283146'),
       ('d6aeab9b-ada8-4318-97d7-bb17ca9dc9b1'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 10.0000, 'AUD',
        'EUR', 6.1000, 0.6100, '2026-06-28 10:09:38.45395'),
       ('768a5ac6-11a7-41b6-9088-1e7805592c89'::uuid, '4b43cf01-fd6b-4fbd-b749-020ed1444552'::uuid, 5.0000, 'AUD',
        'EUR', 3.0500, 0.6100, '2026-06-28 10:26:14.883941');
