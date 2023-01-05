INSERT INTO bank
VALUES ('1fba8a11-f059-4bb3-aa6b-89151886503d', '550182', 'UniCredit Bank', 'http://localhost:8080');

INSERT INTO bank_card
VALUES ('1fba8a11-f059-4bb3-aa6b-891518865011', 'John Doe', '2023-07-01', '$2a$10$I8YehPyNlP7t07605Y6S7OtmP/TOGma9SlhwHKOxQ2FPEDVkUN/92', '$2a$10$Am/XDRwasb9LnzZqjGzlnuo6ocwZkjLqCYq5LQPKH6lxnpdXgkOK.');

INSERT INTO regular_user_account
VALUES ('1fba8a11-f059-4bb3-aa6b-891518865012', 10000, 'John', 0, 'Doe', '1fba8a11-f059-4bb3-aa6b-891518865011');

INSERT INTO merchant_account
VALUES ('1fba8a11-f059-4bb3-aa6b-891518865013', 10000, 'Jane', 0, 'Smith', 'id', 'secret');
