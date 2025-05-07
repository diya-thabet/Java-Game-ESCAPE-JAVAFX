package world1;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Character {

	public String standingImage = "/Down2.png";
	public ImageView character_image;
	public ArrayList<Image> walkingUpImageList; 
	public ArrayList<Image> walkingDownImageList;
	public ArrayList<Image> walkingRightImageList; 
	public ArrayList<Image> walkingLeftImageList; 
		public Character( ImageView character_image) {
			this.character_image = character_image;
;	
			// Initialize the Character's Walking Images Lists
			walkingUpImageList = new ArrayList<Image>();
			walkingUpImageList.add(new Image("/Up1.png"));
			walkingUpImageList.add(new Image("/Up2.png"));
			walkingUpImageList.add(new Image("/Up3.png"));
			
			walkingDownImageList = new ArrayList<Image>();
			walkingDownImageList.add(new Image("/Down1.png"));
			walkingDownImageList.add(new Image("/Down2.png"));
			walkingDownImageList.add(new Image("/Down3.png"));
			
			walkingRightImageList = new ArrayList<Image>();
			walkingRightImageList.add(new Image("/Right1.png"));
			walkingRightImageList.add(new Image("/Right2.png"));
			walkingRightImageList.add(new Image("/Right3.png"));
			
			walkingLeftImageList = new ArrayList<Image>();
			walkingLeftImageList.add(new Image("/Left1.png"));
			walkingLeftImageList.add(new Image("/Left2.png"));
			walkingLeftImageList.add(new Image("/Left3.png"));
			
		}
}
