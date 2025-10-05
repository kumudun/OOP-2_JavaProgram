package dao;

import entity.Currency;          // <-- missing import caused "cannot find symbol"
import java.util.List;

public interface CurrencyDao {
    double getRate(String code) throws Exception;
    List<Currency> findAll() throws Exception;
    void insert(Currency currency) throws Exception;
}
