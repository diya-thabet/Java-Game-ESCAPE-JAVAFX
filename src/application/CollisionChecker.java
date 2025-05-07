package application;

import entity.Player;
import javafx.scene.shape.Rectangle;
import tile.TileManager;

public class CollisionChecker {
	GamePanel gp;
	TileManager tm;
	public CollisionChecker(GamePanel gp,TileManager tm) {
		this.gp=gp;
		this.tm=tm;
	}
	public void CheckTile(int d) {
		int playerLeftWorldX=(int) (gp.player.getWorldX()+ gp.player.solidArea.getX());
		int playerRightWorldX=(int) (gp.player.getWorldX()+ gp.player.solidArea.getX() + gp.player.solidArea.getWidth());
		int playerTopWorldY=(int) (gp.player.getWorldY()+ gp.player.solidArea.getY());
		int playerBottomWorldY=(int) (gp.player.getWorldY()+ gp.player.solidArea.getY() + gp.player.solidArea.getHeight()+10);
		
		int playerLeftCol = playerLeftWorldX/gp.getTileSize();
		int playerRightCol = playerRightWorldX/gp.getTileSize();
		int playerTopRow = playerTopWorldY/gp.getTileSize();
		int playerBottomRow = playerBottomWorldY/gp.getTileSize();
		
		if (gp.moveUp) {
			playerTopRow=(playerTopWorldY+d)/gp.getTileSize();
			if (tm.tiles[tm.mapTileNum[playerLeftCol][playerTopRow]].isCollision()==true || 
					tm.tiles[tm.mapTileNum[playerRightCol][playerTopRow]].isCollision()==true ) {
				gp.player.collisionOn=true;
			}			
		}
		else if (gp.moveDown) {
			playerBottomRow=(playerBottomWorldY+d)/gp.getTileSize();	
			if (tm.tiles[tm.mapTileNum[playerLeftCol][playerBottomRow]].isCollision()==true || 
					tm.tiles[tm.mapTileNum[playerRightCol][playerBottomRow]].isCollision()==true ) {
				gp.player.collisionOn=true;
			}	
		}
		else if (gp.moveLeft) {
			playerLeftCol=(playerLeftWorldX+d)/gp.getTileSize();	
			if (tm.tiles[tm.mapTileNum[playerLeftCol][playerTopRow]].isCollision()==true || 
					tm.tiles[tm.mapTileNum[playerLeftCol][playerBottomRow]].isCollision()==true ) {
				gp.player.collisionOn=true;
			}	
		}
		else if (gp.moveRight) {
			playerRightCol=(playerRightWorldX+d)/gp.getTileSize();	
			if (tm.tiles[tm.mapTileNum[playerRightCol][playerTopRow]].isCollision()==true || 
					tm.tiles[tm.mapTileNum[playerRightCol][playerBottomRow]].isCollision()==true ) {
				gp.player.collisionOn=true;
			}	
		}
	}
/*	private boolean intersect(Rectangle rect1 , Rectangle rect2) {
		if (rect1.getX() < rect2.getX() + rect2.getWidth() &&
			    rect1.getX() + rect1.getWidth() > rect2.getX() &&
			    rect1.getY() < rect2.getY() + rect2.getHeight() &&
			    rect1.getY() + rect1.getHeight() > rect2.getY()) {
			    return true;
			} else {
				return false;
			}
		
		
	}
	public int CheckObject(Player player, int d) {
		int index=-1;
		for(int i=0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				
			player.solidArea.setX(player.solidArea.getX()+ player.getWorldX());
			gp.obj[i].solidArea.setX(player.solidArea.getX()+ player.getWorldX());
			player.solidArea.setY(player.solidArea.getY()+ player.getWorldY());
			gp.obj[i].solidArea.setY(player.solidArea.getY()+ player.getWorldY());
			if (gp.moveUp) {
				player.solidArea.setY(player.solidArea.getY()+d);
				if (intersect(player.solidArea,gp.obj[i].solidArea)) {
					System.out.println("collision up ");
				}
			}
			else if (gp.moveDown) {
				player.solidArea.setY(player.solidArea.getY()+d);
				if (intersect(player.solidArea,gp.obj[i].solidArea)) {
					System.out.println("collision down ");
				}
			}
			else if (gp.moveRight) {
				player.solidArea.setX(player.solidArea.getX()+d);
				if (intersect(player.solidArea,gp.obj[i].solidArea)) {
					System.out.println("collision  right ");
				}
			}
			else if (gp.moveLeft) {
				player.solidArea.setX(player.solidArea.getX()+d);
				if (intersect(player.solidArea,gp.obj[i].solidArea)) {
					System.out.println("collision  right ");
				}
			}
			player.solidArea.setX(player.solidAreaDefaultX);
			gp.obj[i].solidArea.setX(gp.obj[i].solidAreaDefaultX);
			player.solidArea.setY(player.solidAreaDefaultY);
			gp.obj[i].solidArea.setY(gp.obj[i].solidAreaDefaultY);
			}
		}
		return index;
		
	}*/
	private boolean intersect(Rectangle rect1, Rectangle rect2) {
	    return rect1.getX() < rect2.getX() + rect2.getWidth() &&
	           rect1.getX() + rect1.getWidth() > rect2.getX() &&
	           rect1.getY() < rect2.getY() + rect2.getHeight() &&
	           rect1.getY() + rect1.getHeight() > rect2.getY();
	}

	public int CheckObject(Player player, int d) {
	    int index = -1; // Default: no collision

	    for (int i = 0; i < gp.obj.length; i++) {
	        if (gp.obj[i] != null) {

	            double playerX = player.solidArea.getX();
	            double playerY = player.solidArea.getY();
	            double objectX = gp.obj[i].solidArea.getX();
	            double objectY = gp.obj[i].solidArea.getY();


	            player.solidArea.setX(player.getWorldX() + playerX);
	            player.solidArea.setY(player.getWorldY() + playerY);
	            gp.obj[i].solidArea.setX(gp.obj[i].worldX + objectX);
	            gp.obj[i].solidArea.setY(gp.obj[i].worldY + objectY);


	            if (gp.moveUp) {
	                player.solidArea.setY(player.solidArea.getY() + d);
	                if (intersect(player.solidArea, gp.obj[i].solidArea)) {
	                    if(gp.obj[i].collision==true) {
	                    	player.collisionOn=true;	
	                    }
	                    index = i;
	                }
	            } else if (gp.moveDown) {
	                player.solidArea.setY(player.solidArea.getY() + d);
	                if (intersect(player.solidArea, gp.obj[i].solidArea)) {
	                    if(gp.obj[i].collision==true) {
	                    	player.collisionOn=true;	
	                    }
	                    index = i;
	                }
	            } else if (gp.moveRight) {
	                player.solidArea.setX(player.solidArea.getX() + d);
	                if (intersect(player.solidArea, gp.obj[i].solidArea)) {
	                    if(gp.obj[i].collision==true) {
	                    	player.collisionOn=true;	
	                    }
	                    index = i;
	                }
	            } else if (gp.moveLeft) {
	                player.solidArea.setX(player.solidArea.getX() + d);
	                if (intersect(player.solidArea, gp.obj[i].solidArea)) {
	                    if(gp.obj[i].collision==true) {
	                    	player.collisionOn=true;	
	                    }
	                    index = i;
	                }
	            }


	            player.solidArea.setX(playerX);
	            player.solidArea.setY(playerY);
	            gp.obj[i].solidArea.setX(objectX);
	            gp.obj[i].solidArea.setY(objectY);
	        }
	    }

	    return index;
	}

}
