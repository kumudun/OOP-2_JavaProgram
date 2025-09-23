package converter;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Model
        CurrencyModel model = new CurrencyModel();

        // View
        CurrencyView view = new CurrencyView();

        // Controller (per hint: seeds some currencies in its ctor)
        CurrencyController controller = new CurrencyController(model, view);

        // Build scene
        Scene scene = new Scene(view.getRoot(), 560, 420);
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(App.class);
    }
}

