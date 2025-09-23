package converter;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CurrencyModel {

    private final ObservableList<Currency> currencies = FXCollections.observableArrayList();

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    /**
     * Convert between two currencies by using both rates relative to USD.
     * amountInTarget = amount * (targetRate / sourceRate)
     */
    public double convert(double amount, Currency from, Currency to) {
        if (from == null || to == null) throw new IllegalArgumentException("Currencies must be selected.");
        double result = amount * (to.getRateToUSD() / from.getRateToUSD());
        // rounding to 4 decimals for display niceness
        return Math.round(result * 10000.0) / 10000.0;
    }
}


