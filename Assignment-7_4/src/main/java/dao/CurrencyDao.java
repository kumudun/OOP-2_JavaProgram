package dao;

import entity.Currency;
import java.util.List;

public interface CurrencyDao {
    double getRate(String code) throws Exception;
    List<Currency> findAll() throws Exception;
    void insert(Currency currency) throws Exception; // keep if you use the Add-Currency window
}
