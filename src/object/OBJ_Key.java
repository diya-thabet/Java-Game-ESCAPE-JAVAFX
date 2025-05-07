package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OBJ_Key extends SuperObject{
	public OBJ_Key() {
		objImg = new ImageView(new Image(getClass().getResourceAsStream("/key.png")));
		name="Key";
	}
	
}
