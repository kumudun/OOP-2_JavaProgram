-- currency.sql  (idempotent: safe to run repeatedly)

-- 1) Drop & (re)create the database
DROP DATABASE IF EXISTS currencydb;
CREATE DATABASE currencydb;
USE currencydb;

-- 2) Create the table for Currency objects
--    Maps to your Java fields: code, name, rateToUSD
DROP TABLE IF EXISTS currency;
CREATE TABLE currency (
  code CHAR(3)      NOT NULL,              -- e.g., 'USD', 'EUR'
  name VARCHAR(64)  NOT NULL,              -- e.g., 'US Dollar'
  rate_to_usd DECIMAL(12,6) NOT NULL CHECK (rate_to_usd > 0),
  PRIMARY KEY (code)
);

-- 3) Seed with at least eight currencies (rates = USD per 1 unit of currency)
--    These are example values; update if you need today’s exact rates.
INSERT INTO currency (code, name, rate_to_usd) VALUES
('USD','US Dollar',        1.000000),
('EUR','Euro',             1.080000),
('GBP','British Pound',    1.260000),
('JPY','Japanese Yen',     0.006600),
('SEK','Swedish Krona',    0.091000),
('INR','Indian Rupee',     0.012000),
('CAD','Canadian Dollar',  0.730000),
('AUD','Australian Dollar',0.670000),
('CHF','Swiss Franc',      1.100000);

-- 4) Create the application user with minimum required privileges
--    (This app only reads; so grant SELECT only.)
DROP USER IF EXISTS 'appuser'@'localhost';
DROP USER IF EXISTS 'appuser'@'127.0.0.1';

CREATE USER 'appuser'@'localhost'    IDENTIFIED BY 'StrongPwd_123!';
CREATE USER 'appuser'@'127.0.0.1'    IDENTIFIED BY 'StrongPwd_123!';

GRANT SELECT ON currencydb.* TO 'appuser'@'localhost';
GRANT SELECT ON currencydb.* TO 'appuser'@'127.0.0.1';

FLUSH PRIVILEGES;
