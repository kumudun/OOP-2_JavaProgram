package dao;

/**
 * Minimal requirement per assignment:
 * provide a method that returns the exchange rate (double) by currency code.
 */
public interface CurrencyDao {
    /**
     * @param code 3-letter currency code, case-insensitive (e.g. "EUR")
     * @return rate_to_usd for the given code
     * @throws Exception if not found or DB error
     */
    double getRate(String code) throws Exception;
}
