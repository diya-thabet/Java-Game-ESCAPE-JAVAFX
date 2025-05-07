package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OBJ_Door extends SuperObject {
	public OBJ_Door() {
		objImg = new ImageView(new Image(getClass().getResourceAsStream("/door.png")));
		name="Door";
		collision=true;
		
		
	}
	
}
