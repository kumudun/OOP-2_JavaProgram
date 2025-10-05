package controller;

import dao.CurrencyDao;
import dao.JdbcCurrencyDao;
import datasource.MariaDbConnection;
import javafx.scene.control.Alert;
import view.CurrencyView;

/**
 * Application logic: reacts to button clicks and performs the conversion
 * using rates fetched from the database (no hard-coded rates).
 */
public class CurrencyController {

    private final CurrencyView view;
    private final CurrencyDao currencyDao = new JdbcCurrencyDao();

    public CurrencyController(CurrencyView view) {
        this.view = view;
        wireEvents();
    }

    private void wireEvents() {
        view.convertButton.setOnAction(e -> onConvert());
    }

    private void onConvert() {
        view.setError("");
        try {
            double amount = Double.parseDouble(view.amountField.getText().trim());
            String from = view.fromCodeField.getText().trim();
            String to   = view.toCodeField.getText().trim();

            if (from.isBlank() || to.isBlank()) {
                view.setError("Please enter both FROM and TO currency codes, e.g. EUR → USD.");
                return;
            }

            // Fetch live rates from DB
            double fromToUSD = currencyDao.getRate(from);
            double toToUSD   = currencyDao.getRate(to);

            double result = amount * (toToUSD / fromToUSD);
            // round to 4 decimals like your model did
            result = Math.round(result * 10000.0) / 10000.0;

            view.resultField.setText(String.valueOf(result));
        } catch (NumberFormatException nfe) {
            view.setError("Invalid amount. Use numbers like 12.34");
        } catch (IllegalArgumentException iae) {
            // includes "Unknown currency: XXX"
            view.setError(iae.getMessage());
        } catch (Exception ex) {
            // Typical DB unavailability path ends here
            showDbError(ex);
            view.setError("Database error. Please try again later.");
        }
    }

    private void showDbError(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Database connection failed");
        a.setContentText(ex.getMessage());
        a.showAndWait();
        // Do NOT crash; keep UI usable
    }

    /** Ensure a graceful shutdown if you call this on app exit. */
    public void shutdown() {
        MariaDbConnection.terminate();
    }
}
