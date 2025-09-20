package view;


import controller.PetController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Pet;

import java.net.URL;
import java.util.Objects;

public class VirtualPetApplication extends Application {

    private static final double CANVAS_W = 600;
    private static final double CANVAS_H = 400;

    // Tuning
    private static final double SPEED_PX_PER_SEC = 180;  // limit speed
    private static final double ARRIVAL_RADIUS = 6;      // stop near cursor
    private static final double PET_SIZE = 64;           // on-screen size for cat

    private Canvas canvas;
    private GraphicsContext gc;

    private Image petImage;
    private PetController controller;

    private AnimationTimer loop;

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(CANVAS_W, CANVAS_H);
        gc = canvas.getGraphicsContext2D();

        // Load your cat image from: src/main/resources/image/cat.png
        URL url = Objects.requireNonNull(
                getClass().getResource("/image/cat.png"),
                "cat.png not found under src/main/resources/image"
        );
        petImage = new Image(url.toExternalForm());

        // Use a fixed on-screen size so it looks crisp and consistent
        Pet pet = new Pet(CANVAS_W / 2.0, CANVAS_H / 2.0, PET_SIZE, PET_SIZE);
        controller = new PetController(pet, SPEED_PX_PER_SEC, ARRIVAL_RADIUS);

        // Mouse handlers
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> controller.setTarget(e.getX(), e.getY()));
        canvas.addEventHandler(MouseEvent.MOUSE_EXITED, e -> controller.clearTarget()); // requirement 4

        // Scene & stage
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, CANVAS_W, CANVAS_H, Color.LIGHTGRAY);
        stage.setTitle("Virtual Pet (MVC)");
        stage.setScene(scene);
        stage.show();

        // Main loop
        loop = new AnimationTimer() {
            private long lastNanos = -1;

            @Override
            public void handle(long now) {
                if (lastNanos < 0) lastNanos = now;
                double dt = (now - lastNanos) / 1_000_000_000.0;
                lastNanos = now;

                controller.update(dt, CANVAS_W, CANVAS_H);
                draw();
            }
        };
        loop.start();

        draw(); // initial frame
    }

    private void draw() {
        // Clear
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, CANVAS_W, CANVAS_H);

        // Draw pet centered on (x, y)
        double x = controller.getPet().getX();
        double y = controller.getPet().getY();
        double w = controller.getPet().getWidth();
        double h = controller.getPet().getHeight();

        gc.drawImage(petImage, x - w / 2.0, y - h / 2.0, w, h);
    }

    @Override
    public void stop() {
        if (loop != null) loop.stop();
    }
}



