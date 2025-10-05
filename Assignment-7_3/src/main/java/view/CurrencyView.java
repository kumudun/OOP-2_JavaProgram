package view;

import entity.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class CurrencyView {

    private final BorderPane root = new BorderPane();

    public final TextField amountField   = new TextField();
    public final ChoiceBox<Currency> fromChoice = new ChoiceBox<>();
    public final ChoiceBox<Currency> toChoice   = new ChoiceBox<>();
    public final TextField resultField   = new TextField();

    public final Button convertButton    = new Button("Convert");
    public final Button addCurrencyButton = new Button("Add Currency");

    private final Label errorLabel = new Label();

    private final ObservableList<Currency> model = FXCollections.observableArrayList();

    public CurrencyView() {
        build();
    }

    public Pane getRoot() { return root; }

    public ObservableList<Currency> getModel() { return model; }

    public void setError(String msg) {
        boolean show = (msg != null && !msg.isBlank());
        errorLabel.setText(show ? msg : "");
        errorLabel.setVisible(show);
    }

    private void build() {
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(16));

        form.add(new Label("Amount"), 0, 0);
        form.add(amountField, 1, 0);

        form.add(new Label("From"), 0, 1);
        fromChoice.setItems(model);
        form.add(fromChoice, 1, 1);

        form.add(new Label("To"), 0, 2);
        toChoice.setItems(model);
        form.add(toChoice, 1, 2);

        HBox buttons = new HBox(10, convertButton, addCurrencyButton);
        form.add(buttons, 1, 3);

        form.add(new Label("Result"), 0, 4);
        resultField.setEditable(false);
        form.add(resultField, 1, 4);

        errorLabel.getStyleClass().add("error");
        errorLabel.setVisible(false);

        VBox center = new VBox(10, form, errorLabel, new Text(
                "Tip: table currency(code, name, rate_to_usd)."
        ));
        center.setPadding(new Insets(0,16,16,16));

        root.setCenter(center);
    }
}
