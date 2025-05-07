package entity;

import application.CollisionChecker;
import application.GamePanel;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import tile.TileManager;
import world1.Character;

public class Player extends Character {
	GamePanel gp;
	public TileManager tileManager;
	int worldX;
	int worldY;
	public final int screenX;
	public final int screenY;
	double speed;
	int switchWhenZero =0;
    int upCount;
    int downCount;
    int rightCount;
    int leftCount;
    public Rectangle solidArea;
    
    public boolean collisionOn=false;
    CollisionChecker Checker;
	int hasKey=0;
	
	public Player (GamePanel gp , ImageView character_image ) {
		super(character_image);
		this.gp=gp;
		upCount =0;
		downCount=0;
		leftCount =0;
		rightCount =0;
		solidArea = new Rectangle(16,16,16,16);
		screenX= gp.screenWidth/2-gp.getTileSize()/2 ;
		screenY=gp.screenHeight/2- gp.getTileSize()/2;
		setDefaultValues();
		tileManager = new TileManager(gp);
		Checker = new CollisionChecker(gp,tileManager);
		}
	public void setDefaultValues() {
		worldX=gp.getTileSize()*23;
		worldY=gp.getTileSize()*21;	
	}
	public void HandlePlayer() { 
		Player player= this;
        gp.timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				int dx = 0, dy = 0, i;
			    speed = gp.run ? 2 : 1;
			    collisionOn = false;

			    if (gp.moveUp) {
			        dy -= speed;
			        Checker.CheckTile(dy);
			        i = Checker.CheckObject(player, dy);
			        pickUpObject(i);
			        if (!collisionOn) MovePlayer(dx, dy);
			        animatePlayerMovement("UP");
			    } else if (gp.moveDown) {
			        dy += speed;
			        Checker.CheckTile(dy);
			        i = Checker.CheckObject(player, dy);
			        pickUpObject(i);
			        if (!collisionOn) MovePlayer(dx, dy);
			        animatePlayerMovement("DOWN");
			    } else if (gp.moveRight) {
			        dx += speed;
			        Checker.CheckTile(dx);
			        i = Checker.CheckObject(player, dx);
			        pickUpObject(i);
			        if (!collisionOn) MovePlayer(dx, dy);
			        animatePlayerMovement("RIGHT");
			    } else if (gp.moveLeft) {
			        dx -= speed;
			        Checker.CheckTile(dx);
			        i = Checker.CheckObject(player, dx);
			        pickUpObject(i);
			        if (!collisionOn) MovePlayer(dx, dy);
			        animatePlayerMovement("LEFT");
			    } else {
			        character_image.setImage(new Image(standingImage));
			    }

			    // Redraw tiles and objects
			    tileManager.draw();
			    gp.drawObjects();
					
					
			}	
        };
        gp.timer.start();
	}
	private void pickUpObject(int i) {
		if (i!=-1) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Key" : 
				hasKey++;
				System.out.println(hasKey);
				gp.obj[i]=null;
				break;
			case "Door" : 				
				
				if (hasKey > 0) {
					gp.obj[i]=null;
					hasKey--;
				}	
				
				System.out.println(hasKey);
				break;
			case "Chest" : 
				gp.obj[i]=null;
			}
			
				
			
		}
	}
	private void animatePlayerMovement(String direction) {
		switch (direction ) {
		case "UP" : 
			if (switchWhenZero==0) {
        		character_image.setImage(walkingUpImageList.get(upCount % 3)); 
            	upCount++; 
            	switchWhenZero = 17;
            	
			}
			else switchWhenZero--;
			break;
		case "DOWN" : 
			if (switchWhenZero==0) {
        		character_image.setImage(walkingDownImageList.get(downCount % 3)); 
            	downCount++; 
            	switchWhenZero = 17;
            	
			}
			else switchWhenZero--;
			break;
		case "LEFT" : 
			if (switchWhenZero==0) {
        		character_image.setImage(walkingLeftImageList.get(leftCount % 3)); 
            	leftCount++; 
            	switchWhenZero = 17;
            	
			}
			else switchWhenZero--;
			break;	
		case "RIGHT" : 
			if (switchWhenZero==0) {
        		character_image.setImage(walkingRightImageList.get(rightCount % 3)); 
            	rightCount++; 
            	switchWhenZero = 17;
            	
			}
			else switchWhenZero--;
			break;	
		}
		
	}
	private void  MovePlayer(int dx,int dy) {
		if (dx!=0 || dy!=0) {
		worldX+=dx;
		worldY+=dy;
       

		}
	}
	public int getWorldX() {
		return worldX;
	}
	public int getWorldY() {
		return worldY;
	}
	public void draw(Pane pane) {
    	character_image.setLayoutX(screenX);
    	character_image.setLayoutY(screenY);
    	pane.getChildren().add(character_image);
    }
}	
