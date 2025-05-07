package world1;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import application.world2;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class controller2 {
	Pane root;
	Stage stage;
	Scene scene;
    @FXML
    AnchorPane rootPane;
    @FXML 
    StackPane MyStack;
    @FXML 
    Label MyLabel;
    private Character character;
    LinkedList<ObstacleTile> barrier;
    private AnimationTimer timer;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean run = false;
    int upCount;
    int downCount;
    int rightCount;
    int leftCount;
    int switchWhenZero =0;
	int tileWidth = 48;
	int tileHeight= 48;
	boolean foundKey = false;
	boolean nearKey=false;
	
	public void createObstacleTile ( double w, double h, double x, double y ) {
		ObstacleTile tile = new ObstacleTile(w, h, x, y); // rectangle width, height, x_coord, y_coord
		rootPane.getChildren().add(tile);
		barrier.add(tile);
	}
    public void initialize() {
		  ImageView character_image = new ImageView(new Image("/Down2.png"));
		  character_image.setLayoutX(129);  // Starting position
		  character_image.setLayoutY(595);  // Starting position
		  rootPane.getChildren().add(character_image);
		  character = new Character( character_image);
		  barrier = new LinkedList<ObstacleTile>();
		  createObstacleTile(tileWidth*10, tileHeight*3, 0, 0);  // rectangle width, height, x_coord, y_coord		
		  createObstacleTile(tileWidth*3-6, tileHeight*2-6, tileWidth*10, 0);	 // top wall 
		  createObstacleTile(tileWidth*2, tileHeight*6-5, tileWidth*11-9, tileHeight*2-6);  // right wall
		  createObstacleTile(tileWidth*2, tileHeight-6, tileWidth*3, tileHeight*3); //bed second part
		  createObstacleTile(tileWidth, tileHeight, tileWidth*12-6, tileHeight*8);
		  createObstacleTile(tileWidth*2, tileHeight*5, tileWidth*11-6, tileHeight*9);  // right wall
		  createObstacleTile(tileWidth-6, tileHeight*11, 0, tileHeight*3);  // left wall
		  createObstacleTile(tileWidth, tileHeight*4, tileWidth-6, tileHeight*4-3);  // left wall
		  createObstacleTile(tileWidth, tileHeight*2, tileWidth, tileHeight*12);  // left bottom wall
		  createObstacleTile(tileWidth*6+12, tileHeight*2, tileWidth*5-16, tileHeight*12);   // bottom wall
		  createObstacleTile(tileWidth, tileHeight, tileWidth*6-6, tileHeight*11);   // bottom wall
		  createObstacleTile(tileWidth-24, tileHeight-24, tileWidth*2+6, tileHeight*9+9);  // living room chair - left
		  createObstacleTile(tileWidth-24, tileHeight-24, tileWidth*4, tileHeight*9+9);  // living room chair - right
		  createObstacleTile(tileWidth, tileHeight, tileWidth*3-7, tileHeight*9);  // living room table
		  createObstacleTile(tileWidth-14, tileHeight-17, tileWidth*9, tileHeight*3+4);  // square chair
		  createObstacleTile(tileWidth*4, tileHeight*3, tileWidth*7-6, tileHeight*5-4);  // middle wall
		  createObstacleTile(tileWidth, tileHeight*5, tileWidth*6-6, tileHeight*4-3);  // middle wall
		  createObstacleTile(tileWidth*4+10, tileHeight*3+4, tileWidth*7-5, tileHeight*5+4);  // middle wall
		  createObstacleTile(tileWidth,tileHeight*2,tileWidth*5-6,tileHeight*6-6);
		  createObstacleTile(tileWidth*2, tileHeight*2, tileWidth*8-6, tileHeight*10-2);  // kitchen table

        // Add character's ImageView to the game pane
        timer = new AnimationTimer() {
        	
            @Override
            public void handle(long now) {
                int dx = 0;
                int dy = 0;
               // if (prevTime==0 || now - prevTime >=10e6) {
                // switchWhenZero is used because the walking images change too fast - used a count to change image after every 4 rounds
                if (moveUp) {  // Set speed and correct image
                	dy -= 1; 
        			if (switchWhenZero == 0) {
                		character.character_image.setImage(character.walkingUpImageList.get(upCount % 3)); 
	                	upCount++; 
	                	switchWhenZero = 8;
        			}
                	else {
                		switchWhenZero--;
                	}
                }
                else if (moveDown) { 
                	dy += 1; 
        			if (switchWhenZero == 0) {
                		character.character_image.setImage(character.walkingDownImageList.get(downCount % 3)); 
	                	downCount++; 
	                	switchWhenZero = 8;
        			}
                	else {
                		switchWhenZero--;
                	}
                }
                else if (moveRight) { 
                	dx += 1; 
        			if (switchWhenZero == 0) {
                		character.character_image.setImage(character.walkingRightImageList.get(rightCount % 3));  
	                	rightCount++; 
	                	switchWhenZero = 8;
        			}
                	else {
                		switchWhenZero--;
                	}
                }
                else if (moveLeft) { 
                	dx -=1;
        			if (switchWhenZero == 0) {
                		character.character_image.setImage(character.walkingLeftImageList.get(leftCount % 3));  
	                	leftCount++; 
	                	switchWhenZero = 8;
        			}
                	else {
                		switchWhenZero--;
                	}
                }
                if (run) { 
                	dx *= 2; 
                	dy *= 2; 
                }
                
                moveCharacter(dx, dy);
           // }
                //prevTime=now;
           }
        };
        timer.start();
	}

    private boolean hitBarrier(double wantsToGoToThisX, double wantsToGoToThisY) {
    	Iterator<ObstacleTile> it = barrier.iterator();
		while (it.hasNext()) {
			ObstacleTile t = it.next();
			
			double spriteMinX = wantsToGoToThisX + 15;
			double spriteMinY = wantsToGoToThisY + 15;
			double spriteMaxX = wantsToGoToThisX + character.character_image.getBoundsInLocal().getWidth() - 10; 
			double spriteMaxY = wantsToGoToThisY + character.character_image.getBoundsInLocal().getHeight();

			double tMinX = t.getX();
			double tMinY = t.getY();
			double tMaxX = t.getX() + t.getWidth();
			double tMaxY = t.getY() + t.getHeight();
			
			boolean inside = spriteMaxX > tMinX && spriteMinX < tMaxX && spriteMaxY > tMinY && spriteMinY < tMaxY;

			if (inside) {
				return true;
			}
		}
    	return false;
    }

    public void onKeyPressed(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());
        switch (event.getCode()) {
            case RIGHT -> moveRight = true;
            case LEFT -> moveLeft = true;
            case UP -> moveUp = true;
            case DOWN -> moveDown = true;
            case SHIFT -> run = true;
            case SPACE ->{ 
	        	if (nearKey) {
    	        	foundKey = true; 	
    	        	MyLabel.setText("Got Key!");
    	        	MyStack.setOpacity(1);
    	            
    	            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    	            pause.setOnFinished(ev -> { MyStack.setOpacity(0); });
    	            pause.play();

	        	}
	        	 if (nearKey==false && foundKey== false) {
    	        	MyLabel.setText("Didn't find key.");
    	        	MyStack.setOpacity(1);
    	            
    	            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    	            pause.setOnFinished(ev -> { MyStack.setOpacity(0); });
    	            pause.play();
	        	}
	        	 if (foundKey==true && nearKey==false) {
    	        	MyLabel.setText("Key Found ! ESCAPE !");
    	        	MyStack.setOpacity(1);
    	            
    	            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
    	            pause.setOnFinished(ev -> { MyStack.setOpacity(0); });
    	            pause.play();
	        	}
            }
        }
    }

    public void onKeyReleased(KeyEvent event) {
        System.out.println("Key released: " + event.getCode());
        switch (event.getCode()) {
            case RIGHT -> moveRight = false;
            case LEFT -> moveLeft = false;
            case UP -> moveUp = false;
            case DOWN -> moveDown = false;
            case SHIFT -> run = false;
        }
    }
    private void moveCharacter(int dx, int dy) {
        if (dx != 0 || dy != 0) {  // Only move if character has "speed" - added/subtracted from the key presses
	        double cx = character.character_image.getBoundsInLocal().getWidth()  / 2;
	        double cy = character.character_image.getBoundsInLocal().getHeight() / 2;
	 
	        double x = cx + character.character_image.getLayoutX() + dx;
	        double y = cy + character.character_image.getLayoutY() + dy;
	        
	        // Check if character should move
	        if ( x - cx >= 0 && 
	        		x + cx <= 612 &&  // Scene width
	                y - cy >= 0 &&
	                y + cy <= 664 &&
	                !hitBarrier(x-cx,y-cy)) // Scene height
	                 {
	        	character.character_image.relocate(x - cx, y - cy); 
	            }

	        // Check if character is near the key
	        if (x  >= 478 && x  <= 501 && y  >= 104 && y <= 133) { nearKey = true; }
	        else nearKey=false;
	        
	        // Check if character found key and is near the door
	        // Won the game!
	        if (foundKey && x >= 97 && x <= 225 && y >= 632 && y <= 668) {
	        	timer.stop(); // Need to end animation timer or else it will keep entering this if statement and pop up the win screen infinitely
	        	world2 World2 = new world2();
	        	stage = (Stage) character.character_image.getScene().getWindow();
	        	World2.start1(stage);
	        	/* try {
	        	   root = FXMLLoader.load(getClass().getResource("/world1/WonScreen.fxml"));
		   		   stage = (Stage)character.character_image.getScene().getWindow();
	    		   scene = new Scene(root);
	    		   stage.setScene(scene);
	    		   root.requestFocus();
	    		   stage.show();
	        
		    		
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
 
	    		// Set background image

	        }
        }
        
        else { character.character_image.setImage(new Image(character.standingImage)); }  // if not moving, set character image to standing image

    }
}