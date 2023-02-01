
INSERT INTO bank
VALUES ('1fba8a11-f059-4bb3-aa6b-89151886503d', '402670', 'Erste Bank', 'http://localhost:8088');

INSERT INTO bank_card
VALUES ('1fba8a11-f059-4bb3-aa6b-891518865011', 'Jill Doe', '2023-05-01', '$2a$10$F1STcSik6DDguvUGDcNKYel7zOvOTHsRg8MTBVL1lrEwEtm0a1o9u', '$2a$10$Et.WK3gYSvpK9Q3IAmq9hORh.R4lIi/8dEF7YmCDlssMZPIgQZUM6');

INSERT INTO regular_user_account (id, name, surname, account, reserved_assets, available_assets, bank_card_id)
VALUES ('1fba8a11-f059-4bb3-aa6b-891518865012', 'Jill', 'Doe', '402-1234567891234-12', 0, 10000, '1fba8a11-f059-4bb3-aa6b-891518865011');

INSERT INTO merchant_account (id, name, surname, account, reserved_assets, available_assets, merchant_id,
                              merchant_password, company_name)
VALUES ('1fba8a11-f059-4bb3-aa6b-891518865013', 'Kevin', 'Smith', '402-1234567894321-12', 0, 10000, 'id', '$2a$10$RRmMpZg1KJMygzA6JJCDCuUYm9KM8onr3ikePOw8XdHa4VETBvrtm', 'StaffMate');
