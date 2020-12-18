BEGIN;

DROP TABLE IF EXISTS market CASCADE;
CREATE TABLE market (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO market (title, price) VALUES
('american bobtail cat', 10000),
('bengal cat', 20000),
('sphynx cat', 15000),
('norwegian forest cat', 50000),
('savannah cat', 500000);


COMMIT;