package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import object.SuperObject;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GamePanel {
    // SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    // WOLRD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public boolean moveRight = false;
    public boolean moveLeft = false;
    public boolean moveUp = false;
    public boolean moveDown = false;
    public boolean run = false;
    private Pane root;
    private Pane tileLayer;
    private Pane characterLayer;
    private Pane objectLayer;
    private Scene scene; 
    public AnimationTimer timer;
    public Player player;
    public ObjectSetter objSetter = new ObjectSetter(this); ;
    public SuperObject obj[] = new SuperObject[10];
    // Constructor
    public GamePanel() {
        root = new Pane();
        tileLayer = new Pane();
        characterLayer = new Pane();
        objectLayer = new Pane();
        root.setPrefSize(screenWidth, screenHeight);
        //tileLayer.setPrefSize(screenWidth, screenHeight);
        root.setStyle("-fx-background-color: black;");// Set background color to black
        root.getChildren().addAll(tileLayer, characterLayer,objectLayer);
        scene = new Scene(root, screenWidth, screenHeight);
        ImageView character_image = new ImageView(new Image("/Down2.png"));
        player= new Player(this, character_image);
        objSetter.setObject();      
    }
    public void  drawObjects() {
    	objectLayer.getChildren().clear();
    	for(int i=0 ; i < obj.length; i++) {
    		if (obj[i]!= null) {
    			obj[i].draw(this);    		}
    	}
    }
    public void KeyHandeler() {
    	 scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

 			@Override
 			public void handle(KeyEvent e) {
 				KeyCode code = e.getCode();
                 if (code == KeyCode.UP) { moveUp = true; }
     	        else if (code == KeyCode.DOWN) { moveDown = true; }
     	        else if (code == KeyCode.RIGHT) { moveRight  = true; }
     	        else if (code == KeyCode.LEFT) { moveLeft  = true; }
     	        else if (code == KeyCode.SHIFT) { run = true; }
 				
 			}        	
         });
         scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent e) {
             	KeyCode code = e.getCode();
                 if (code == KeyCode.UP) { moveUp = false; }
     	        else if (code == KeyCode.DOWN) { moveDown = false; }
     	        else if (code == KeyCode.RIGHT) { moveRight  = false; }
     	        else if (code == KeyCode.LEFT) { moveLeft  = false; }
     	        else if (code == KeyCode.SHIFT) { run = false; }
             }
         });
    }

    // Getter for root (if needed for further customization)
    public Pane getTileLayer(){
    	return tileLayer;
    }
    public Pane getRoot() {
        return root;
    }
    public Pane getCharacterLayer() {
    	return characterLayer;
    }
    public Pane getObjectLayer() {
    	return objectLayer;
    }

    // Getter for tile size (used by other classes like TileManager)
    public int getTileSize() {
        return tileSize;
    }

    // Getter for scene
    public Scene getScene() {
        return scene;
    }
}
