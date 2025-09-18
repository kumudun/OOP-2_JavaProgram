package view;



import controller.DictionaryController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Dictionary;

public class DictionaryView extends Application {

    private DictionaryController controller;
    private TextField input;
    private TextArea output;
    private Label feedback;

    @Override
    public void init() {
        controller = new DictionaryController(new Dictionary());
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Virtual Dictionary");

        Label prompt = new Label("Word:");
        input = new TextField();
        input.setPromptText("e.g. java");
        Button search = new Button("Search");
        output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        feedback = new Label();

        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.TOP_LEFT);
        root.setPadding(new Insets(12));
        root.getChildren().addAll(prompt, input, search, output, feedback);

        search.setOnAction(e -> performSearch());
        input.setOnAction(e -> performSearch());

        stage.setScene(new Scene(root));
        stage.show();
    }

    private void performSearch() {
        String result = controller.search(input.getText());
        if ("Please enter a word.".equals(result) || "Word not found in dictionary.".equals(result)) {
            feedback.setText(result);
            output.clear();
        } else {
            feedback.setText("");
            output.setText(result);
        }
    }
}

