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

    private void startGame(Stage stage) {
        try {
            Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            arena = new Arena(WIDTH, HEIGHT);
            snake = new Snake(WIDTH / 2, HEIGHT / 2); // Posisi awal ular di tengah arena
            spawnFood();

            gameOverXPosition = WIDTH * TILE_SIZE;

            Timeline snakeTimeline = new Timeline(new KeyFrame(Duration.millis(250), e -> run(gc)));
            snakeTimeline.setCycleCount(Timeline.INDEFINITE);

            Timeline gameOverTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> moveGameOverText(gc)));
            gameOverTimeline.setCycleCount(Timeline.INDEFINITE);

            Scene scene = createScene(canvas);

            stage.setScene(scene);
            stage.setTitle("Snake Game");
            stage.show();
            snakeTimeline.play();
            gameOverTimeline.play();
        } catch (Exception e) {
            System.err.println("Error initializing the game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void run(GraphicsContext gc) {
        try {
            if (gameOver) {
                drawGameOver(gc);
                return;
            }

            snake.move();

            int[] head = snake.getBody().get(0);
            if (head[0] == food.getX() && head[1] == food.getY()) {
                snake.grow();
                score += 10;
                spawnFood();
                return;
            }

            if (head[0] < 0 || head[1] < 0 || head[0] >= WIDTH || head[1] >= HEIGHT) {
                gameOver = true;
            }

            for (int i = 1; i < snake.getBody().size(); i++) {
                int[] bodyPart = snake.getBody().get(i);
                if (head[0] == bodyPart[0] && head[1] == bodyPart[1]) {
                    gameOver = true;
                    break;
                }
            }

            arena.draw(gc);

            gc.setFill(Color.RED);
            gc.setFont(new Font("Goudy Stout", 15));
            gc.fillText("Score: " + score, 10, 20);

            snake.draw(gc);
            food.draw(gc);
        } catch (Exception e) {
            System.err.println("Error during game loop: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void spawnFood() {
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(WIDTH);
            y = random.nextInt(HEIGHT);
        } while (snake.isBody(x, y));

        food = new Food(x, y);
        food.randomizeType();
    }

    private void drawGameOver(GraphicsContext gc) {
        arena.draw(gc);

        gc.setFill(Color.RED);
        gc.setFont(new Font("Kristen ITC", 40));
        String gameOverText = "GAME OVER";
        double gameOverXPos = gameOverXPosition;
        double gameOverYPos = HEIGHT * TILE_SIZE / 2;
        gc.fillText(gameOverText, gameOverXPos, gameOverYPos);

        gc.setFont(new Font("Jokerman", 18));
        String scoreText = "\n\nSELAMAT!!! " + username.toUpperCase() + "\n SCORE ANDA " + score;
        double scoreTextXPos = (WIDTH * TILE_SIZE - getTextWidth(scoreText, gc)) / 2;
        gc.setFill(Color.BLUE);
        gc.fillText(scoreText, scoreTextXPos, HEIGHT * TILE_SIZE / 2 + 50);

        gc.setFont(new Font("Castellar", 20));
        String restartText = "\n\n\nPress ENTER to Restart";
        double restartTextXPos = (WIDTH * TILE_SIZE - getTextWidth(restartText, gc)) / 2;
        gc.setFill(Color.BLACK);
        gc.fillText(restartText, restartTextXPos, HEIGHT * TILE_SIZE / 2 + 100);
    }

    private double getTextWidth(String text, GraphicsContext gc) {
        Text t = new Text(text);
        t.setFont(gc.getFont());
        return t.getLayoutBounds().getWidth();
    }

    private void moveGameOverText(GraphicsContext gc) {
        if (gameOver) {
            gameOverXPosition -= 10;

            if (gameOverXPosition + 300 < 0) {
                gameOverXPosition = WIDTH * TILE_SIZE;
            }
        }
    }

    private Scene createScene(Canvas canvas) {
        Scene scene = new Scene(new javafx.scene.Group(canvas));
        scene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            String currentDirection = snake.getDirection();

            if (gameOver && code == KeyCode.ENTER) {
                resetGame();
            } else {
                switch (code) {
                    case W: if (!currentDirection.equals("DOWN")) snake.setDirection("UP"); break;
                    case S: if (!currentDirection.equals("UP")) snake.setDirection("DOWN"); break;
                    case A: if (!currentDirection.equals("RIGHT")) snake.setDirection("LEFT"); break;
                    case D: if (!currentDirection.equals("LEFT")) snake.setDirection("RIGHT"); break;
                }
            }
        });
        return scene;
    }

    private void resetGame() {
        score = 0;
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        spawnFood();
        gameOver = false;
        gameOverXPosition = WIDTH * TILE_SIZE;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
