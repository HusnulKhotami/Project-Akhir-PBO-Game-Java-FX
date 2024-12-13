import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.util.Random;

public class AlurGame extends Application {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int TILE_SIZE = 25;
    private static int score = 0;
    private String username;

    private Arena arena;
    private Snake snake;
    private Food food;
    private boolean gameOver = false;
    private double gameOverXPosition;
    private double gameOverDelay = 0;

    @Override
    public void start(Stage stage) {
        try {
            showLoginMenu(stage);
        } catch (Exception e) {
            System.err.println("Error initializing the game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showLoginMenu(Stage stage) {
        Label labelUsername = new Label("Username:");
        TextField inputUsername = new TextField();
        
        Label labelPassword = new Label("Password:");
        PasswordField inputPassword = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String usernameInput = inputUsername.getText().trim();
            String password = inputPassword.getText().trim();

            if (!usernameInput.isEmpty() && !password.isEmpty()) {
                this.username = usernameInput;
                System.out.println("Login berhasil dengan Username: " + username);
                startGame(stage);
            } else {
                System.out.println("Username dan Password tidak boleh kosong.");
            }
        });

        VBox vbox = new VBox(10, labelUsername, inputUsername, labelPassword, inputPassword, loginButton);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene loginScene = new Scene(vbox, 300, 300);
        stage.setScene(loginScene);
        stage.setTitle("Login");
        stage.show();
    }

    