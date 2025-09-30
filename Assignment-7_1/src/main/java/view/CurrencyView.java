package view;

import model.Currency;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CurrencyView {

    private final BorderPane root = new BorderPane();

    // Exposed to controller
    public final TextField amountField = new TextField();
    public final TextField resultField = new TextField();
    public final ChoiceBox<Currency> fromChoice = new ChoiceBox<>();
    public final ChoiceBox<Currency> toChoice = new ChoiceBox<>();
    public final Button convertButton = new Button("Convert");

    private final Label errorLabel = new Label();

    public CurrencyView() {
        buildUI();
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setVisible(msg != null && !msg.isBlank());
    }

    private void buildUI() {
        Label title = new Label("Currency Converter");
        title.getStyleClass().add("app-title");

        HBox top = new HBox(title);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(16, 16, 12, 16));
        root.setTop(top);

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(12);
        form.setPadding(new Insets(16));

        form.add(new Label("Amount"), 0, 0);
        amountField.setPromptText("e.g., 123.45");
        form.add(amountField, 0, 1);

        VBox fromBox = new VBox(6, new Label("From currency"), fromChoice);
        form.add(fromBox, 1, 0, 1, 2);

        VBox toBox = new VBox(6, new Label("To currency"), toChoice);
        form.add(toBox, 2, 0, 1, 2);

        HBox buttonRow = new HBox(convertButton);
        buttonRow.setAlignment(Pos.CENTER_LEFT);
        form.add(buttonRow, 0, 2);

        form.add(new Label("Result"), 0, 3);
        resultField.setEditable(false);
        form.add(resultField, 0, 4);

        errorLabel.getStyleClass().add("error");
        errorLabel.setVisible(false);
        form.add(errorLabel, 0, 5, 3, 1);

        root.setCenter(form);

        Text instructions = new Text(
                "How to use:\n" +
                        "1) Enter the amount (numbers only).\n" +
                        "2) Choose the source currency (From) and the target currency (To).\n" +
                        "3) Click “Convert” to compute the result."
        );
        instructions.setTextAlignment(TextAlignment.LEFT);

        VBox bottom = new VBox(new Separator(), instructions);
        bottom.setPadding(new Insets(12,16,16,16));
        root.setBottom(bottom);
    }
}
