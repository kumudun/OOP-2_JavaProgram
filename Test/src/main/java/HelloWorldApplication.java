
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloWorldApplication extends Application {
    public void start(Stage window) {
        Label text = new Label("Hello World!");
        Scene view = new Scene(text);
        window.setTitle("My Greeting Application");
        window.setScene(view);
        window.show();
    }
}