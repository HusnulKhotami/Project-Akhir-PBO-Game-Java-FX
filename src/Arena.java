import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arena {
    private static final int TILE_SIZE = 25;
    private int width, height;

    private Color backgroundColor = Color.LIGHTBLUE; 
    private Color gridColor = Color.WHITE;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public void setGridColor(Color color) {
        this.gridColor = color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, width * TILE_SIZE, height * TILE_SIZE);
        
        gc.setStroke(gridColor);
        for (int i = 0; i <= width; i++) {
            gc.strokeLine(i * TILE_SIZE, 0, i * TILE_SIZE, height * TILE_SIZE);
        }
        for (int j = 0; j <= height; j++) {
            gc.strokeLine(0, j * TILE_SIZE, width * TILE_SIZE, j * TILE_SIZE);
        }
    }
}
