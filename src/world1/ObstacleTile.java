package world1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObstacleTile extends Rectangle {
	public ObstacleTile(double width, double height, double x, double y) {
		super(width, height);  // 50, 53
		setFill(Color.rgb(0, 0, 0, 0));  
		setX(x);
		setY(y);
	}
}