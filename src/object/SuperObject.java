package object;

import application.GamePanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class SuperObject {
	public ImageView objImg;
	public String name;
	public boolean collision = false ;
	public int worldX,worldY;
	public  Rectangle solidArea = new Rectangle(0,0,48,48);
	public void draw(GamePanel gp) {
		
		int screenX=worldX-gp.player.getWorldX() + gp.player.screenX+6;
		int screenY=worldY-gp.player.getWorldY() + gp.player.screenY+6;
		if (worldX + gp.getTileSize() > gp.player.getWorldX() - gp.player.screenX &&
			worldX < gp.player.getWorldX() + gp.player.screenX + gp.getTileSize() &&
			worldY + gp.getTileSize() > gp.player.getWorldY() - gp.player.screenY &&
			worldY < gp.player.getWorldY() + gp.player.screenY + gp.getTileSize()) {

			if (name=="Door" || name =="Chest") {
				objImg.setFitHeight(48);
				objImg.setFitWidth(48);
				objImg.setLayoutX(screenX-6);
				objImg.setLayoutY(screenY-6);
			}
			else {
				objImg.setFitHeight(36);
				objImg.setFitWidth(36);
				objImg.setLayoutX(screenX);
				objImg.setLayoutY(screenY);
			}

			gp.getObjectLayer().getChildren().add(objImg);
			}

	}
}
