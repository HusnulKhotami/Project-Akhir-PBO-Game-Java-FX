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
    // method untuk menentukan jenis objek makananan secara acak
    public void randomizeType() {
        foodType = random.nextInt(5) + 1; // Pilih angka 1-5
    }


    
