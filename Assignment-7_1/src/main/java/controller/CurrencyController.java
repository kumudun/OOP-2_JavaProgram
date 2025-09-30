package controller;

import dao.CurrencyDao;
import dao.JdbcCurrencyDao;
import datasource.Db;
import model.Currency;
import model.CurrencyModel;
import view.CurrencyView;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class CurrencyController {

    private final CurrencyModel model;
    private final CurrencyView view;

    public CurrencyController(CurrencyModel model, CurrencyView view) {
        this.model = model;
        this.view = view;

        // --- Load currencies from DB
        try {
            CurrencyDao dao = new JdbcCurrencyDao(new Db());
            model.getCurrencies().setAll(dao.findAll());
        } catch (Exception e) {
            // Fallback seed so app remains usable
            model.getCurrencies().setAll(
                    new Currency("USD", "US Dollar", 1.00),
                    new Currency("EUR", "Euro", 1.08),
                    new Currency("GBP", "British Pound", 1.26)
            );
            view.setError("DB unavailable — using built-in sample rates.");
            System.err.println("DB load failed: " + e.getMessage());
        }

        // Bind model to view
        view.fromChoice.setItems(model.getCurrencies());
        view.toChoice.setItems(model.getCurrencies());

        // Defaults
        view.fromChoice.getSelectionModel().selectFirst();
        if (model.getCurrencies().size() > 1) {
            view.toChoice.getSelectionModel().select(1);
        }

        // Restrict numeric input
        applyNumericFormatter();

        // Button action
        view.convertButton.setOnAction(e -> onConvert());

        // Clear error on input change
        view.amountField.textProperty().addListener(this::clearErrorOnChange);
        view.fromChoice.valueProperty().addListener(this::clearErrorOnChange);
        view.toChoice.valueProperty().addListener(this::clearErrorOnChange);
    }

    private void applyNumericFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String t = change.getControlNewText();
            if (t.isEmpty()) return change;
            int dots = t.replace(",", ".").length() - t.replace(",", ".").replace(".", "").length();
            return (t.matches("[0-9.,]*") && dots <= 1) ? change : null;
        };
        view.amountField.setTextFormatter(new TextFormatter<>(filter));
    }

    private void clearErrorOnChange(ObservableValue<?> o, Object ov, Object nv) {
        view.setError("");
    }

    private void onConvert() {
        try {
            String raw = view.amountField.getText().trim();
            if (raw.isEmpty()) { view.setError("Enter an amount to convert."); return; }
            double amount = Double.parseDouble(raw.replace(',', '.'));
            if (amount < 0) { view.setError("Amount must be non-negative."); return; }

            Currency from = view.fromChoice.getValue();
            Currency to   = view.toChoice.getValue();
            if (from == null || to == null) { view.setError("Select both currencies."); return; }

            double result = model.convert(amount, from, to);
            view.resultField.setText(String.valueOf(result));
            view.setError("");
        } catch (NumberFormatException nfe) {
            view.setError("Invalid number format.");
        } catch (IllegalArgumentException iae) {
            view.setError(iae.getMessage());
        } catch (Exception ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Unexpected error");
            a.setContentText(ex.getMessage());
            a.showAndWait();
        }
    }
}
