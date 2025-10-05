package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * Minimal, assignment-focused UI: user types FROM/TO codes and amount.
 * Controller handles the DB work and fills result / error.
 */
public class CurrencyView {

    private final BorderPane root = new BorderPane();

    public final TextField amountField   = new TextField();
    public final TextField fromCodeField = new TextField(); // e.g. "EUR"
    public final TextField toCodeField   = new TextField(); // e.g. "USD"
    public final TextField resultField   = new TextField();
    public final Button convertButton    = new Button("Convert");

    private final Label errorLabel = new Label();

    public CurrencyView() {
        build();
    }

    public Pane getRoot() { return root; }

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

        form.add(new Label("From (code)"), 0, 1);
        form.add(fromCodeField, 1, 1);

        form.add(new Label("To (code)"), 0, 2);
        form.add(toCodeField, 1, 2);

        form.add(convertButton, 1, 3);

        form.add(new Label("Result"), 0, 4);
        resultField.setEditable(false);
        form.add(resultField, 1, 4);

        errorLabel.getStyleClass().add("error");
        errorLabel.setVisible(false);

        VBox center = new VBox(10, form, errorLabel, new Text(
                "Tip: codes are 3 letters in table currency(code, name, rate_to_usd)."
        ));
        center.setPadding(new Insets(0,16,16,16));

        root.setCenter(center);
    }
}
