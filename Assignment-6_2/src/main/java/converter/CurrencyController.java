package converter;


import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextFormatter;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.UnaryOperator;

public class CurrencyController {

    private final CurrencyModel model;
    private final CurrencyView view;

    public CurrencyController(CurrencyModel model, CurrencyView view) {
        this.model = model;
        this.view = view;

        // (Hint) Hardcode a few currencies initially
        // Rates relative to USD (example placeholder rates)
        model.getCurrencies().addAll(
                new Currency("USD", "US Dollar", 1.00),
                new Currency("EUR", "Euro", 1.08),
                new Currency("GBP", "British Pound", 1.26),
                new Currency("JPY", "Japanese Yen", 0.0066),
                new Currency("SEK", "Swedish Krona", 0.091),
                new Currency("INR", "Indian Rupee", 0.012)
        );

        // Populate the choice boxes from the model
        view.fromChoice.setItems(model.getCurrencies());
        view.toChoice.setItems(model.getCurrencies());

        // Choose sensible defaults
        view.fromChoice.getSelectionModel().selectFirst();
        if (model.getCurrencies().size() > 1) {
            view.toChoice.getSelectionModel().select(1);
        }

        // Restrict amount input to numeric (digits + one decimal separator , or .)
        applyNumericFormatter();

        // Wire button
        view.convertButton.setOnAction(e -> onConvert());

        // Clear error message when inputs change
        view.amountField.textProperty().addListener(this::clearErrorOnChange);
        view.fromChoice.valueProperty().addListener(this::clearErrorOnChange);
        view.toChoice.valueProperty().addListener(this::clearErrorOnChange);
    }

    private void applyNumericFormatter() {
        char localeDecimal = DecimalFormatSymbols.getInstance(Locale.getDefault()).getDecimalSeparator();

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();

            // Allow empty (so user can type), digits, and at most one '.' or ','
            if (newText.isEmpty()) return change;

            // Normalize commas to dots internally later; for the field we allow both
            int dots = newText.replace(",", ".").length() - newText.replace(",", ".").replace(".", "").length();

            if (newText.matches("[0-9.,]*") && dots <= 1) {
                return change;
            }
            return null; // reject change
        };
        view.amountField.setTextFormatter(new TextFormatter<>(filter));
    }

    private void clearErrorOnChange(ObservableValue<?> obs, Object oldVal, Object newVal) {
        view.setError("");
    }

    private void onConvert() {
        try {
            String raw = view.amountField.getText().trim();
            if (raw.isEmpty()) {
                view.setError("Enter an amount to convert.");
                return;
            }

            // Accept both '.' and ',' as decimal separator
            double amount = Double.parseDouble(raw.replace(',', '.'));

            if (amount < 0) {
                view.setError("Amount must be non-negative.");
                return;
            }

            Currency from = view.fromChoice.getValue();
            Currency to = view.toChoice.getValue();

            if (from == null || to == null) {
                view.setError("Select both source and target currencies.");
                return;
            }

            double result = model.convert(amount, from, to);

            // Display result (keep user-friendly formatting)
            view.resultField.setText(String.valueOf(result));

            // Clear any prior error
            view.setError("");

        } catch (NumberFormatException nfe) {
            view.setError("Invalid number format. Use digits and optional decimal separator.");
        } catch (IllegalArgumentException iae) {
            view.setError(iae.getMessage());
        } catch (Exception ex) {
            // Last resort—show alert so user isn't stuck
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Unexpected error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}


