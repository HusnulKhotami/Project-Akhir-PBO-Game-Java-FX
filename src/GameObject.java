public abstract class GameObject {
    private int x;
    private int y;

    // Constructor menggunakan setter agar data tervalidasi dengan baik
    public GameObject(int x, int y) {
        setX(x);
        setY(y);
    }

    // Getter dan Setter untuk atribut x
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // Getter dan Setter untuk atribut y
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Abstract method draw, yang wajib diimplementasikan oleh subclass
    public abstract void draw(javafx.scene.canvas.GraphicsContext gc);
}
