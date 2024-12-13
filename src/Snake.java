import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Snake extends GameObject {
    private ArrayList<int[]> body = new ArrayList<>();
    private String direction = "RIGHT";
    private boolean alive = true;

    // Constructor memanggil konstruktor superclass (GameObject)
    public Snake(int startX, int startY) {
        super(startX, startY);
        body.add(new int[]{startX, startY});
    }

    public ArrayList<int[]> getBody() {
        return body;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // Metode untuk menggerakkan ular
    public void move() {
        if (!alive) return;

        int[] head = body.get(0);
        int newX = head[0];
        int newY = head[1];

        switch (direction) {
            case "UP": newY--; break;
            case "DOWN": newY++; break;
            case "LEFT": newX--; break;
            case "RIGHT": newX++; break;
        }

        body.add(0, new int[]{newX, newY});
        body.remove(body.size() - 1);
    }

    // Metode untuk menambah panjang ular
    public void grow() {
        int[] tail = body.get(body.size() - 1);
        body.add(new int[]{tail[0], tail[1]});
    }

    // Mengecek apakah posisi (x, y) berada di badan ular (kecuali kepala)
    public boolean isBody(int x, int y) {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i)[0] == x && body.get(i)[1] == y) {
                return true;
            }
        }
        return false;
    }

    // Implementasi metode abstrak draw dari GameObject
    @Override
    public void draw(GraphicsContext gc) {
        for (int i = 0; i < body.size(); i++) {
            int[] part = body.get(i);

            if (i == 0) {  // Kepala ular
                gc.setFill(Color.BLUE);
                gc.fillRect(part[0] * 25, part[1] * 25, 25, 25);
            } else {  // Ekor ular
                // Ganti warna setiap kelipatan 4
                if (i % 4 == 0) {
                    gc.setFill(Color.GREEN);  // Ular bagian kelipatan 4 berwarna hijau
                } else {
                    gc.setFill(Color.PINK);  // Bagian lain berwarna pink
                }

                gc.fillRect(part[0] * 25, part[1] * 25, 25, 25);

                // Pola garis-garis pada ekor
                gc.setStroke(Color.MAGENTA);
                gc.setLineWidth(2);

                for (int j = 0; j < 25; j += 4) {
                    gc.strokeLine(part[0] * 25 + j, part[1] * 25, part[0] * 25 + j, part[1] * 25 + 25);
                }
            }
        }
    }
}
