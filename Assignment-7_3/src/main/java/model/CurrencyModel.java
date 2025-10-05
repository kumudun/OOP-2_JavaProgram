package model;

import entity.Currency;                      // <-- use your entity
import javafx.collections.FXCollections;     // <-- JavaFX collection helpers
import javafx.collections.ObservableList;    // <-- JavaFX observable list
import java.util.Collection;

public class CurrencyModel {

    private final ObservableList<Currency> currencies =
            FXCollections.observableArrayList();

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    /** Convenience if you refresh from DAO and want to show in the UI. */
    public void setCurrencies(Collection<Currency> items) {
        currencies.setAll(items);
    }

    /** Pure calculation using the entity's rateToUSD values. */
    public double convert(double amount, Currency from, Currency to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Currencies must be selected.");
        }
        double result = amount * (to.getRateToUSD() / from.getRateToUSD());
        return Math.round(result * 10000.0) / 10000.0;
    }
}
