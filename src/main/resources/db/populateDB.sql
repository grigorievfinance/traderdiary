DELETE FROM user_role;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@mail.com', 'password'),
       ('Admin', 'admin@mail.com', 'admin'),
       ('Guest', 'guest@mail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

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