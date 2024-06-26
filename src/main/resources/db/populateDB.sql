DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, balance)
VALUES ('User', 'user@mail.com', 'password', 2005),
       ('Admin', 'admin@mail.com', 'admin', 1900),
       ('Guest', 'guest@mail.com', 'guest', 2000);

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001);

INSERT INTO position (date_time, symbol, profit, user_id)
VALUES ('2020-01-30 10:00:00', 'BNBUSDT', 500, 100000),
       ('2020-01-30 13:00:00', 'BNBUSDT', 1000, 100000),
       ('2020-01-30 20:00:00', 'BNBUSDT', 500, 100000),
       ('2020-01-31 0:00:00', 'BNBUSDT', 100, 100000),
       ('2020-01-31 10:00:00', 'BNBUSDT', 500, 100000),
       ('2020-01-31 13:00:00', 'BNBUSDT', 1000, 100000),
       ('2020-01-31 20:00:00', 'BNBUSDT', 510, 100000),
       ('2020-01-31 14:00:00', 'BTCUSDT', 510, 100001),
       ('2020-01-31 21:00:00', 'ETHUSDT', 1500, 100001);