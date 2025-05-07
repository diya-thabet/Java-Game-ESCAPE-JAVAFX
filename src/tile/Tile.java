package tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile {
 
    private boolean collision = false; 
    private Image image;

    public Tile(Image image) {
        this.image = image;
    }

    public void drawTile(int x, int y, Pane pane) {
        // Create a new ImageView for each tile to avoid duplication
        ImageView tileImageView = new ImageView(image);
        tileImageView.setFitWidth(48); // Adjust to tile size
        tileImageView.setFitHeight(48);
        tileImageView.setLayoutX(x);
        tileImageView.setLayoutY(y);

        // Add the tile's ImageView to the pane
        pane.getChildren().add(tileImageView);
    }
    public Image getImage() {
        return image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
