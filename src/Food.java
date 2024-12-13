import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food extends GameObject {
    private int foodType; // Menyimpan jenis makanan (1-5)
    private static final Random random = new Random();
    private static final int SIZE = 25; // Ukuran grid

    public Food(int x, int y) {
        super(x, y);
        randomizeType(); // Pilih jenis makanan secara acak
    }

    // Metode untuk menentukan jenis makanan secara acak
    public void randomizeType() {
        foodType = random.nextInt(5) + 1; // Pilih angka 1-5
    }

    @Override
    public void draw(GraphicsContext gc) {
        switch (foodType) {
            case 1:
                drawHammer(gc);
                break;
            case 2:
                drawApple(gc);
                break;
            case 3:
                drawStar(gc);
                break;
            case 4:
                drawGoldBox(gc);
                break;
            case 5:
                drawHeart(gc);
                break;
            default:
                drawDefault(gc);
        }
    }

    // Menggambar palu
    private void drawHammer(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(getX() * SIZE + 5, getY() * SIZE + 5, 15, 5); // Kepala palu
        gc.setFill(Color.BROWN);
        gc.fillRect(getX() * SIZE + 10, getY() * SIZE + 10, 5, 10); // Pegangan palu
    }

    // Menggambar apel
    private void drawApple(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(getX() * SIZE + 5, getY() * SIZE + 5, 15, 15); // Badan apel
        gc.setFill(Color.GREEN);
        gc.fillRect(getX() * SIZE + 12, getY() * SIZE, 5, 5); // Daun apel
    }

    // Menggambar bintang
    private void drawStar(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillPolygon(
            new double[]{getX() * SIZE + 12, getX() * SIZE + 15, getX() * SIZE + 20, getX() * SIZE + 17, getX() * SIZE + 22, getX() * SIZE + 12, getX() * SIZE + 2, getX() * SIZE + 7, getX() * SIZE, getX() * SIZE + 10},
            new double[]{getY() * SIZE, getY() * SIZE + 10, getY() * SIZE + 10, getY() * SIZE + 15, getY() * SIZE + 22, getY() * SIZE + 17, getY() * SIZE + 22, getY() * SIZE + 15, getY() * SIZE + 10, getY() * SIZE + 10},
            10
        );
    }

    // Menggambar kotak emas
    private void drawGoldBox(GraphicsContext gc) {
        gc.setFill(Color.GOLD);
        gc.fillRect(getX() * SIZE + 5, getY() * SIZE + 5, 15, 15);
    }

    // Menggambar hati
    private void drawHeart(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(getX() * SIZE + 5, getY() * SIZE + 7, 10, 10); // Lingkaran kiri
        gc.fillOval(getX() * SIZE + 10, getY() * SIZE + 7, 10, 10); // Lingkaran kanan
        gc.fillPolygon(
            new double[]{getX() * SIZE + 5, getX() * SIZE + 20, getX() * SIZE + 12.5},
            new double[]{getY() * SIZE + 12, getY() * SIZE + 12, getY() * SIZE + 25},
            3
        ); // Segitiga bawah
    }

    // Menggambar makanan default
    private void drawDefault(GraphicsContext gc) {
        gc.setFill(Color.RED); // Warna default
        gc.fillOval(getX() * SIZE, getY() * SIZE, SIZE, SIZE);
    }
}
