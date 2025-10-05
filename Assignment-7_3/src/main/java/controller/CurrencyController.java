package controller;

import dao.CurrencyDao;
import dao.JpaCurrencyDao;
import datasource.MariaDbJpaConnection;
import entity.Currency;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import view.AddCurrencyView;
import view.CurrencyView;

import java.util.List;

public class CurrencyController {

    private final CurrencyView view;
    private final CurrencyDao currencyDao = new JpaCurrencyDao();

    public CurrencyController(CurrencyView view) {
        this.view = view;
        wireEvents();
        loadCurrencies();
    }

    private void wireEvents() {
        view.convertButton.setOnAction(e -> onConvert());
        view.addCurrencyButton.setOnAction(e -> onAddCurrency());
    }

    private void loadCurrencies() {
        view.setError("");
        Platform.runLater(() -> {
            try {
                List<Currency> list = currencyDao.findAll();
                view.getModel().setAll(list);
                if (!list.isEmpty()) {
                    if (view.fromChoice.getValue() == null) view.fromChoice.setValue(list.get(0));
                    if (view.toChoice.getValue() == null && list.size() > 1) view.toChoice.setValue(list.get(1));
                }
            } catch (Exception ex) {
                showDbError(ex);
                view.setError("Cannot load currencies from database.");
            }
        });
    }

    private void onConvert() {
        view.setError("");
        try {
            double amount = Double.parseDouble(view.amountField.getText().trim());
            Currency from = view.fromChoice.getValue();
            Currency to   = view.toChoice.getValue();
            if (from == null || to == null) {
                view.setError("Select both FROM and TO currencies.");
                return;
            }
            double fromToUSD = currencyDao.getRate(from.getCode());
            double toToUSD   = currencyDao.getRate(to.getCode());
            double result = amount * (toToUSD / fromToUSD);
            result = Math.round(result * 10000.0) / 10000.0;
            view.resultField.setText(String.valueOf(result));
        } catch (NumberFormatException nfe) {
            view.setError("Invalid amount. Use numbers like 12.34");
        } catch (IllegalArgumentException iae) {
            view.setError(iae.getMessage());
        } catch (Exception ex) {
            showDbError(ex);
            view.setError("Database error. Please try again later.");
        }
    }

    private void onAddCurrency() {
        Stage owner = (Stage) view.getRoot().getScene().getWindow();
        AddCurrencyView.showModal(owner, (code, name, rate) -> {
            currencyDao.insert(new Currency(code, name, rate));
        });
        loadCurrencies(); // refresh after modal closes
    }

    private void showDbError(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Database / JPA error");
        a.setContentText(ex.getMessage());
        a.showAndWait();
    }

    public void shutdown() {
        MariaDbJpaConnection.closeAll();
    }
}
