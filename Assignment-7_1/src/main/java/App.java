import controller.CurrencyController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CurrencyModel;
import view.CurrencyView;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Model
        CurrencyModel model = new CurrencyModel();

        // View
        CurrencyView view = new CurrencyView();

        // Controller
        new CurrencyController(model, view);

        // Scene
        Scene scene = new Scene(view.getRoot(), 560, 420);
        var cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(App.class);
    }
}




