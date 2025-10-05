package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Currency;   // <-- add this import

public class CurrencyModel {

    private final ObservableList<Currency> currencies =
            FXCollections.observableArrayList();

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    public double convert(double amount, Currency from, Currency to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Currencies must be selected.");
        }
        double result = amount * (to.getRateToUSD() / from.getRateToUSD());
        return Math.round(result * 10000.0) / 10000.0;
    }
}
