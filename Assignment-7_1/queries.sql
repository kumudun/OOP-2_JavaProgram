USE currencydb;

-- 1) Retrieve all currencies
SELECT code, name, rate_to_usd
FROM currency
ORDER BY code;

-- 2) Retrieve the currency with abbreviation 'EUR'
SELECT code, name, rate_to_usd
FROM currency
WHERE code = 'EUR';

-- 3) Retrieve the number of currencies
SELECT COUNT(*) AS currency_count
FROM currency;

-- 4) Retrieve the currency with the highest exchange rate
SELECT code, name, rate_to_usd
FROM currency
ORDER BY rate_to_usd DESC
LIMIT 1;
