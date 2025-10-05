package application;

import controller.CurrencyController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.CurrencyView;

public class App extends Application {

    private CurrencyController controller;

    @Override
    public void start(Stage stage) {
        CurrencyView view = new CurrencyView();
        controller = new CurrencyController(view);
        stage.setTitle("JPA-Enabled Currency Converter");
        stage.setScene(new Scene(view.getRoot(), 560, 320));
        stage.show();
    }

    @Override
    public void stop() {
        if (controller != null) controller.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
