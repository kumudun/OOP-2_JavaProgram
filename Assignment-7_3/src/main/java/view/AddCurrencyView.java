package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddCurrencyView {

    public interface SaveHandler {
        void onSave(String code, String name, double rate) throws Exception;
    }

    public static void showModal(Stage owner, SaveHandler handler) {
        Stage stage = new Stage();
        stage.initOwner(owner);
        stage.setTitle("Add Currency");
        stage.initModality(Modality.WINDOW_MODAL);

        TextField codeField = new TextField();
        TextField nameField = new TextField();
        TextField rateField = new TextField();

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        Label error = new Label();
        error.getStyleClass().add("error");
        error.setVisible(false);

        save.setOnAction(e -> {
            error.setVisible(false);
            try {
                String code = codeField.getText().trim().toUpperCase();
                String name = nameField.getText().trim();
                double rate = Double.parseDouble(rateField.getText().trim());
                if (code.length() != 3 || name.isBlank() || rate <= 0) {
                    throw new IllegalArgumentException("Enter 3-letter code, name, and positive rate.");
                }
                handler.onSave(code, name, rate);
                stage.close();
            } catch (Exception ex) {
                error.setText(ex.getMessage());
                error.setVisible(true);
            }
        });
        cancel.setOnAction(e -> stage.close());

        GridPane gp = new GridPane();
        gp.setHgap(10); gp.setVgap(10); gp.setPadding(new Insets(16));
        gp.addRow(0, new Label("Code (3 letters)"), codeField);
        gp.addRow(1, new Label("Name"), nameField);
        gp.addRow(2, new Label("Rate to USD"), rateField);
        gp.addRow(3, save, cancel);
        gp.add(error, 0, 4, 2, 1);

        stage.setScene(new Scene(gp, 380, 220));
        stage.showAndWait(); // main stage waits
    }
}
