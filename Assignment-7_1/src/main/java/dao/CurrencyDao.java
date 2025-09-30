package dao;

import model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {
    List<Currency> findAll() throws Exception;
    Optional<Currency> findByCode(String code) throws Exception;

    // Optional helper if you ever want to update rates:
    void upsert(Currency currency) throws Exception;
}
