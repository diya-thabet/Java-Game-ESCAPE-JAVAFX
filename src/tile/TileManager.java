package tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import application.GamePanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TileManager {
    private GamePanel gp; // Assuming GamePanel is converted to JavaFX
    public Tile[] tiles;
    public int mapTileNum[][];

    // Constructor
    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[10];
        getTileImage(); // Initialize tile images 
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        loadMap();
    }
    public void loadMap() {
    	try {
    		InputStream is = getClass().getResourceAsStream("/world01.txt");
    		BufferedReader  br = new BufferedReader(new InputStreamReader(is));
    		int col = 0 ;
    		int row = 0 ;
    		String line;
    		while ( col < gp.maxWorldCol && row < gp.maxWorldRow) {
    			line = br.readLine();
    			String number[] = line.split(" ");
    			while (col < gp.maxWorldCol) {
    				int num = Integer.parseInt(number[col]);
    				mapTileNum[col][row]=num;
    				col++;
    			}
    			col=0;
    			row++;
    		}
    		br.close();
    		
    		
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    // Load tile images
    public void getTileImage() {
        try {
            // Create tile instances and load their images
            tiles[0] = new Tile(new Image(getClass().getResourceAsStream("/grass.png")));
            
            tiles[1] = new Tile(new Image(getClass().getResourceAsStream("/wall.png")));
            tiles[1].setCollision(true);
            
            tiles[2] = new Tile(new Image(getClass().getResourceAsStream("/water.png")));
            tiles[2].setCollision(true);
            
            tiles[3] = new Tile(new Image(getClass().getResourceAsStream("/earth.png")));
            
            tiles[4] = new Tile(new Image(getClass().getResourceAsStream("/tree.png")));
            tiles[4].setCollision(true);
            
            tiles[5] = new Tile(new Image(getClass().getResourceAsStream("/sand.png")));
            
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Draw tiles on a Pane
    public void draw() {   
    	gp.getTileLayer().getChildren().clear();
    	int col=0;
    	int row=0;
    	while( col < gp.maxWorldCol && row < gp.maxWorldRow) {
    		int worldX=col*gp.getTileSize();
    		int  worldY=row*gp.getTileSize();	
    		int screenX=worldX-gp.player.getWorldX() + gp.player.screenX;
    		int screenY=worldY-gp.player.getWorldY() + gp.player.screenY;
    		if (worldX + gp.getTileSize() > gp.player.getWorldX() - gp.player.screenX &&
    			    worldX < gp.player.getWorldX() + gp.player.screenX + gp.getTileSize() &&
    			    worldY + gp.getTileSize() > gp.player.getWorldY() - gp.player.screenY &&
    			    worldY < gp.player.getWorldY() + gp.player.screenY + gp.getTileSize()) {
    			    tiles[mapTileNum[col][row]].drawTile(screenX, screenY, gp.getTileLayer());
    			}


    		col++;
    		if (col == gp.maxWorldCol) {
    			col=0;
    			row++;
    		}
    		
    	}                 
    }
}
