import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food extends GameObject {
    private int foodType; 
    private static final Random random = new Random();

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
            case 1: // Palu
                gc.setFill(Color.GRAY);
                gc.fillRect(getX() * 25 + 5, getY() * 25 + 5, 15, 5); // Kepala palu
                gc.setFill(Color.BROWN);
                gc.fillRect(getX() * 25 + 10, getY() * 25 + 10, 5, 10); // Pegangan palu
                break;

            case 2: // Apel
                gc.setFill(Color.RED);
                gc.fillOval(getX() * 25 + 5, getY() * 25 + 5, 15, 15); // Badan apel
                gc.setFill(Color.GREEN);
                gc.fillRect(getX() * 25 + 12, getY() * 25, 5, 5); // Daun apel
                break;

            case 3: // Bintang
                gc.setFill(Color.YELLOW);
                gc.fillPolygon(
                    new double[]{getX() * 25 + 12, getX() * 25 + 15, getX() * 25 + 20, getX() * 25 + 17, getX() * 25 + 22, 
                                 getX() * 25 + 12, getX() * 25 + 2, getX() * 25 + 7, getX() * 25, getX() * 25 + 10},
                    new double[]{getY() * 25, getY() * 25 + 10, getY() * 25 + 10, getY() * 25 + 15, getY() * 25 + 22, 
                                 getY() * 25 + 17, getY() * 25 + 22, getY() * 25 + 15, getY() * 25 + 10, getY() * 25 + 10},
                    10
                );
                break;

            case 4: // Kotak emas
                gc.setFill(Color.GOLD);
                gc.fillRect(getX() * 25 + 5, getY() * 25 + 5, 15, 15);
                break;

            case 5: // Hati
                gc.setFill(Color.RED);
                gc.fillOval(getX() * 25 + 5, getY() * 25 + 7, 10, 10); // Lingkaran kiri
                gc.fillOval(getX() * 25 + 10, getY() * 25 + 7, 10, 10); // Lingkaran kanan
                gc.fillPolygon(
                    new double[]{getX() * 25 + 5, getX() * 25 + 20, getX() * 25 + 12.5},
                    new double[]{getY() * 25 + 12, getY() * 25 + 12, getY() * 25 + 25},
                    3
                ); // Segitiga bawah
                break;

            default:
                gc.setFill(Color.RED); // Warna default
                gc.fillOval(getX() * 25, getY() * 25, 25, 25);
        }
    }
}
