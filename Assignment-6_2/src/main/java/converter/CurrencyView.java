package converter;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CurrencyView {

    private final BorderPane root = new BorderPane();

    // Exposed to controller
    final TextField amountField = new TextField();
    final TextField resultField = new TextField();
    final ChoiceBox<Currency> fromChoice = new ChoiceBox<>();
    final ChoiceBox<Currency> toChoice = new ChoiceBox<>();
    final Button convertButton = new Button("Convert");

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
        // ---- TOP: Title bar (HBox inside BorderPane TOP) ----
        Label title = new Label("Currency Converter");
        title.getStyleClass().add("app-title");

        HBox top = new HBox(title);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(16, 16, 12, 16));
        root.setTop(top);
        BorderPane.setAlignment(top, Pos.CENTER);

        // ---- CENTER: Main form (GridPane) ----
        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(12);
        form.setPadding(new Insets(16));

        // amount input
        Label amountLabel = new Label("Amount");
        amountField.setPromptText("e.g., 123.45");
        form.add(amountLabel, 0, 0);
        form.add(amountField, 0, 1);

        // source currency (label on top of choice box)
        VBox fromBox = new VBox(6, new Label("From currency"), fromChoice);
        form.add(fromBox, 1, 0, 1, 2);

        // target currency (label on top of choice box)
        VBox toBox = new VBox(6, new Label("To currency"), toChoice);
        form.add(toBox, 2, 0, 1, 2);

        // convert button (HBox centered)
        HBox buttonRow = new HBox(convertButton);
        buttonRow.setAlignment(Pos.CENTER_LEFT);
        buttonRow.setSpacing(8);
        form.add(buttonRow, 0, 2);

        // result (read-only)
        Label resultLabel = new Label("Result");
        resultField.setEditable(false);
        form.add(resultLabel, 0, 3);
        form.add(resultField, 0, 4);

        // error label (styled via CSS)
        errorLabel.getStyleClass().add("error");
        errorLabel.setVisible(false);
        form.add(errorLabel, 0, 5, 3, 1);

        // Make columns grow nicely
        ColumnConstraints c0 = new ColumnConstraints();
        c0.setPercentWidth(40);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(30);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(30);
        form.getColumnConstraints().addAll(c0, c1, c2);

        root.setCenter(form);

        // ---- BOTTOM: Instructions (VBox inside BorderPane BOTTOM) ----
        Text instructions = new Text(
                """
                How to use:
                1) Enter the amount (numbers only; decimal separator '.' or ',').
                2) Choose the source currency (From) and the target currency (To).
                3) Click “Convert” to compute the result.
                """
        );
        instructions.setTextAlignment(TextAlignment.LEFT);

        VBox bottom = new VBox();
        bottom.setPadding(new Insets(12,16,16,16));
        bottom.setSpacing(6);
        bottom.getChildren().addAll(new Separator(), instructions);

        root.setBottom(bottom);
    }
}

