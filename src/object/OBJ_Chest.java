package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OBJ_Chest extends SuperObject {
	public OBJ_Chest() {
		objImg = new ImageView(new Image(getClass().getResourceAsStream("/chest.png")));
		name="Chest";
	}
	
}
