import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {
    private int score = 0;

    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");

        // Step 5
        Label scoreLabel = new Label("Score: 0");
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // Step 7
        Button button[] = new Button[8];
        Random random = new Random();
        for (int i = 0; i < button.length; i++) {
            Button dButton = new Button();
            if (i != 0) {
                dButton.setText("Desert");
            } else {
                dButton.setText("Dessert");
            }

            // Step 9
            dButton.setOnAction(event -> {
                if (dButton.getText().equals("Desert")) {
                    score--;
                } else {
                    score++;
                }

                // Step 10
                scoreLabel.setText("Score: " + score);
                randomizeButtonPositions(random, button);
                exitButton.requestFocus();
            });
            button[i] = dButton;
            pane.getChildren().add(button[i]);
        }
        randomizeButtonPositions(random, button);
        stage.setScene(scene);
        stage.show();

    }

    // Step 8
    private void randomizeButtonPositions(Random random, Button button[]){
        for (int i = 0; i < button.length; i++) {
            button[i].setLayoutX(random.nextInt(600));
            button[i].setLayoutY(random.nextInt(400));
        }
    }


    public static void main(String[] args) {
        Application.launch();
    }
}
